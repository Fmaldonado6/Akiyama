import { MatDialogRef } from '@angular/material/dialog';
import { NavigationExtras, Router } from '@angular/router';
import { AnimeService } from './../../../../services/api/anime.service';
import { ServerModel } from './../../../../models/api/apiContent/ServerModel';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'server-layout',
  templateUrl: './server-layout.component.html',
  styleUrls: ['./server-layout.component.css']
})
export class ServerLayoutComponent implements OnInit {
  Status = Status
  currentStatus = Status.loading

  @Input() servers: ServerModel[]
  @Output() back = new EventEmitter()
  @Output() serverSelected = new EventEmitter()

  @Input() episodeId

  constructor(
    private animeService: AnimeService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.servers == null)
      this.getServers()
    else
      this.currentStatus = Status.loaded
  }

  getServers() {
    this.currentStatus = Status.loading

    this.animeService.getEpisodeServers(this.episodeId).subscribe(servers => {
      this.servers = servers.servers
      this.currentStatus = Status.loaded
    })
  }

  changeRoute(url) {
    this.serverSelected.emit()
    let extras: NavigationExtras = {
      queryParams: {
        url: url
      }
    }


    this.router.navigate(["/watch"], extras)
  }

  backPressed() {
    this.back.emit()
  }

}

enum Status {
  loading = 0,
  loaded,
  error
}