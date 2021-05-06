import { NavigationExtras, Router } from '@angular/router';
import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { Episode, Server, Status } from 'src/app/core/models/modelst';
import { Component, Inject, OnInit, Output, EventEmitter } from '@angular/core';
import { MAT_BOTTOM_SHEET_DATA } from '@angular/material/bottom-sheet';

interface SheetData {
  episode: Episode
}

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.scss']
})
export class ServersComponent implements OnInit {
  Status = Status
  currentStatus = Status.loading
  episode: Episode
  servers: Server[] = []
  @Output() serverSelected = new EventEmitter()
  constructor(
    private animeService: AnimeService,
    private router: Router,
    @Inject(MAT_BOTTOM_SHEET_DATA) private sheetData: SheetData
  ) { }

  ngOnInit(): void {
    this.episode = this.sheetData.episode
    if (!this.episode.servers)
      this.getServers()
    else {
      this.servers = this.episode.servers
      this.currentStatus = Status.loaded
    }
  }

  openServer(server: Server) {
    const extras: NavigationExtras = {
      queryParams: {
        url: server.code
      }
    }

    this.serverSelected.emit()

    this.router.navigate([`/watch`], extras)
  }

  getServers() {
    this.currentStatus = Status.loading
    this.animeService.getEpisoderServers(this.episode.id).subscribe(e => {
      this.servers = e
      this.currentStatus = Status.loaded
    }, () => {
      this.currentStatus = Status.error
    })
  }

}
