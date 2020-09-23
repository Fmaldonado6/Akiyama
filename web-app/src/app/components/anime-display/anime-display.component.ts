import { AnimeService } from './../../services/api/anime.service';
import { Component, OnInit } from '@angular/core';
import { EpisodeModel } from 'src/app/models/api/apiContent/EpisodeModel';

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

  scroll(element: HTMLElement, event) {
    let scrollValue = element.scrollLeft
    let scrollAmount = event.deltaY < 0 ? scrollValue + 150 : scrollValue - 150
    element.scroll({ left: scrollAmount })
  }

}
