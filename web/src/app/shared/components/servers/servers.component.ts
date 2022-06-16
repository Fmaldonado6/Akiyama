import { Subscription } from 'rxjs';
import { MatDialogRef } from '@angular/material/dialog';
import { NavigationExtras, Router, ActivatedRoute } from '@angular/router';
import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { Episode, Server, Status } from 'src/app/core/models/modelst';
import { Component, Inject, OnInit, Output, EventEmitter, OnDestroy } from '@angular/core';
import { MAT_BOTTOM_SHEET_DATA, MatBottomSheet, MatBottomSheetRef } from '@angular/material/bottom-sheet';

interface SheetData {
  episode: Episode
}

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.scss']
})
export class ServersComponent implements OnInit, OnDestroy {
  Status = Status
  currentStatus = Status.loading
  episode: Episode
  servers: Server[] = []
  subscription: Subscription
  requestSubscription: Subscription
  @Output() serverSelected = new EventEmitter()
  constructor(
    private animeService: AnimeService,
    private router: Router,
    private bottomSheet: MatBottomSheetRef<ServersComponent>,
    @Inject(MAT_BOTTOM_SHEET_DATA) private sheetData: SheetData
  ) { }
  ngOnDestroy(): void {
    if (this.subscription)
      this.subscription.unsubscribe()
  }

  ngOnInit(): void {
    this.episode = this.sheetData.episode
    if (!this.episode.servers)
      this.getServers()
    else {
      this.servers = this.episode.servers
      this.currentStatus = Status.loaded
    }

    this.subscription = this.bottomSheet.afterDismissed().subscribe(e => {
      if (this.requestSubscription)
        this.requestSubscription.unsubscribe()
    })
  }

  openServer(server: Server) {
    const extras: NavigationExtras = {
      queryParams: {
        url: server.url
      }
    }

    this.serverSelected.emit()

    this.animeService.lastRoute = this.router.url


    this.router.navigate([`/watch`], extras)
  }

  getServers() {
    this.currentStatus = Status.loading
    this.requestSubscription = this.animeService.getEpisoderServers(this.episode.id).subscribe(e => {
      this.servers = e
      this.currentStatus = Status.loaded
    }, () => {
      this.currentStatus = Status.error
    })
  }

}
