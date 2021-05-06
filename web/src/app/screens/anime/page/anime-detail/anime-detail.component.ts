import { Anime, Status } from 'src/app/core/models/modelst';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-anime-detail',
  templateUrl: './anime-detail.component.html',
  styleUrls: ['./anime-detail.component.scss']
})
export class AnimeDetailComponent implements OnInit {

  anime: Anime
  Status = Status
  currentStatus = Status.loading
  constructor() { }

  ngOnInit(): void {

    if (history.state.data) {
      this.anime = history.state.data
      this.currentStatus = Status.loading
    }
  }

}
