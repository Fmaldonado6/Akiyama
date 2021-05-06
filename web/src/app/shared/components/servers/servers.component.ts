import { AnimeService } from 'src/app/core/services/anime/anime.service';
import { Episode, Server, Status } from 'src/app/core/models/modelst';
import { Component, Inject, OnInit } from '@angular/core';
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
  constructor(
    private animeService: AnimeService,
    @Inject(MAT_BOTTOM_SHEET_DATA) private sheetData: SheetData
  ) { }

  ngOnInit(): void {
    this.episode = this.sheetData.episode
    this.getServers()
  }

  getServers() {
    this.animeService.getEpisoderServers(this.episode.id).subscribe(e => {
      this.servers = e
      this.currentStatus = Status.loaded
    })
  }

}
