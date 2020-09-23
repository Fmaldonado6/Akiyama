import { EpisodeModel } from './../../models/api/EpisodeModel';
import { AnimeService } from './../../services/api/anime.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'anime-display',
  templateUrl: './anime-display.component.html',
  styleUrls: ['./anime-display.component.css']
})
export class AnimeDisplayComponent implements OnInit {

  episodes: EpisodeModel[]

  constructor(private animeService: AnimeService) {
    this.getData()
  }

  ngOnInit() {
  }

  getData() {
    this.animeService.getLatestEpisodes().subscribe(episodes => {
      console.log(episodes)
      this.episodes = episodes.episodes
    })

  }

}
