import { NavigationExtras, Router } from '@angular/router';
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
    private animeService: AnimeService,
    private router: Router
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

    if (this.animeService.animes.length != 0) {
      this.animeInfo.animeList = this.animeService.animes
      this.animeInfo.currentStatus = Status.loaded
      return
    }

    this.animeService.fetchAnimes().subscribe(e => {
      this.animeInfo.animeList = e
      this.animeService.animes = e
      this.animeInfo.currentStatus = Status.loaded
    })
  }

  getMovies() {
    if (this.animeService.movies.length != 0) {
      this.moviesInfo.animeList = this.animeService.movies
      this.moviesInfo.currentStatus = Status.loaded
      return
    }
    this.animeService.fetchMovies().subscribe(e => {
      this.moviesInfo.animeList = e
      this.animeService.movies = e
      this.moviesInfo.currentStatus = Status.loaded
    })
  }

  getEpisodes() {
    if (this.animeService.episodes.length != 0) {
      this.episodesInfo.episodeList = this.animeService.episodes
      this.episodesInfo.currentStatus = Status.loaded
      return
    }
    this.animeService.fetchEpisodes().subscribe(e => {
      this.episodesInfo.episodeList = e
      this.animeService.episodes = e
      this.episodesInfo.currentStatus = Status.loaded
    })
  }

  getSpecials() {
    if (this.animeService.specials.length != 0) {
      this.specialsInfo.animeList = this.animeService.specials
      this.specialsInfo.currentStatus = Status.loaded
      return
    }
    this.animeService.fetchSpecials().subscribe(e => {
      this.specialsInfo.animeList = e
      this.animeService.specials = e
      this.specialsInfo.currentStatus = Status.loaded
    })
  }

  getOvas() {
    if (this.animeService.ovas.length != 0) {
      this.ovasInfo.animeList = this.animeService.ovas
      this.ovasInfo.currentStatus = Status.loaded
      return
    }
    this.animeService.fetchOvas().subscribe(e => {
      this.ovasInfo.animeList = e
      this.animeService.ovas = e
      this.ovasInfo.currentStatus = Status.loaded
    })
  }

}
