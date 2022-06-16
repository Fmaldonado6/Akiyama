import { catchError, map, timeout } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Anime, Episode, Server } from '../../models/modelst';
import { DataService } from '../data.service';

@Injectable({
  providedIn: 'root'
})
export class AnimeService extends DataService {

  animeObserver = new BehaviorSubject<Anime[]>([])
  animes = []
  ovas = []
  specials = []
  movies = []
  episodes = []

  lastSearch = []
  lastQuery = ""

  lastAnimeOpened?: Anime = null

  lastRoute?: string = null

  setAnimes(animes: Anime[]) {
    this.animes = animes
    this.animeObserver.next(animes)
  }

  setOvas(ovas: Anime[]) {
    this.ovas = ovas
  }

  setSpecials(specials: Anime[]) {
    this.specials = specials

  }

  setMovies(movies: Anime[]) {
    this.movies = movies
  }

  setEpisodes(episodes: Episode[]) {
    this.episodes = episodes
  }

  fetchEpisodes() {
    return this.http.get<Episode[]>(`${this.url}/animes/episodes`).pipe(timeout(10000), catchError(this.handleError))
  }

  fetchAnimes() {
    return this.http.get<Anime[]>(`${this.url}/animes/latest`).pipe(timeout(10000),
      catchError(this.handleError),
      map(e => {
        this.setAnimes(e)
        return e
      }))
  }

  fetchSpecials() {
    return this.http.get<Anime[]>(`${this.url}/animes/specials`).pipe(timeout(10000), catchError(this.handleError))
  }



  fetchOvas() {
    return this.http.get<Anime[]>(`${this.url}/animes/ovas`).pipe(timeout(10000), catchError(this.handleError))
  }

  fetchMovies() {
    return this.http.get<Anime[]>(`${this.url}/animes/movies`).pipe(timeout(10000), catchError(this.handleError))
  }

  getAnimeInfo(id: string) {
    return this.http.get<Anime>(`${this.url}/animes/${id}`).pipe(timeout(10000), catchError(this.handleError))

  }

  getAnimeImage(url: string) {
    return this.http.get(url, { responseType: 'blob' })
    
  }

  getEpisoderServers(id: string) {
    return this.http.get<Server[]>(`${this.url}/animes/episodes/${id}`).pipe(timeout(10000), catchError(this.handleError))
  }

  search(query: string) {
    return this.http.get<Anime[]>(`${this.url}/animes/search/${query}`).pipe(timeout(10000), catchError(this.handleError))
  }

}
