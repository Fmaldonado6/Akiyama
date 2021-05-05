import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Anime, Episode, InfoItem, Status, Type } from 'src/app/core/models/modelst';
import { AnimeService } from 'src/app/core/services/anime/anime.service';


@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss']
})
export class PageComponent implements OnInit, OnDestroy {

  animeInfo = new InfoItem()
  episodesInfo = new InfoItem(Type.Episode)
  specialsInfo = new InfoItem()
  ovasInfo = new InfoItem()
  moviesInfo = new InfoItem()

  constructor(
    private animeService: AnimeService
  ) { }

  ngOnDestroy(): void {
  }

  ngOnInit(): void {
    this.getAnimes()
    this.getEpisodes()
    this.getMovies()
    this.getSpecials()
    this.getOvas()
  }

  getAnimes() {
    this.animeService.fetchAnimes().subscribe(e => {
      this.animeInfo.animeList = e
      this.animeInfo.currentStatus = Status.loaded
    })
  }

  getMovies() {
    this.animeService.fetchMovies().subscribe(e => {
      this.moviesInfo.animeList = e
      this.moviesInfo.currentStatus = Status.loaded
    })
  }

  getEpisodes() {
    this.animeService.fetchEpisodes().subscribe(e => {
      this.episodesInfo.episodeList = e
      this.episodesInfo.currentStatus = Status.loaded
    })
  }

  getSpecials() {
    this.animeService.fetchSpecials().subscribe(e => {
      this.specialsInfo.animeList = e
      this.specialsInfo.currentStatus = Status.loaded
    })
  }

  getOvas() {
    this.animeService.fetchOvas().subscribe(e => {
      this.ovasInfo.animeList = e
      this.ovasInfo.currentStatus = Status.loaded
    })
  }

}
