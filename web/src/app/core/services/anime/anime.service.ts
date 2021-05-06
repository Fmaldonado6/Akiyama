import { catchError, map } from 'rxjs/operators';
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
    return this.http.get<Episode[]>(`${this.url}/animes/episodes`).pipe(catchError(this.handleError))
  }

  fetchAnimes() {
    return this.http.get<Anime[]>(`${this.url}/animes/latest`).pipe(
      catchError(this.handleError),
      map(e => {
        this.setAnimes(e)
        return e
      }))
  }

  fetchSpecials() {
    return this.http.get<Anime[]>(`${this.url}/animes/specials`).pipe(catchError(this.handleError))
  }

  fetchOvas() {
    return this.http.get<Anime[]>(`${this.url}/animes/ovas`).pipe(catchError(this.handleError))
  }

  fetchMovies() {
    return this.http.get<Anime[]>(`${this.url}/animes/movies`).pipe(catchError(this.handleError))
  }

  getAnimeInfo(id: string, name: string) {
    return this.http.get<Anime>(`${this.url}/animes/${id}/${name}`).pipe(catchError(this.handleError))

  }

  getEpisoderServers(id: string) {
    return this.http.get<Server[]>(`${this.url}/animes/episodes/${id}`).pipe(catchError(this.handleError))
  }

  search(query: string) {
    return this.http.get<Anime[]>(`${this.url}/animes/search/${query}`).pipe(catchError(this.handleError))
  }

}
