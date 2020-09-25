import { InfoResponse, OvaResponse, SpecialsResponse, SearchResponse, DataType } from './../../models/api/responses/responsesSchema';
import { DataService } from './../data.service';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { AnimeResponse, EpisodeResponse, MovieResponse, ServerResponse } from 'src/app/models/api/responses/responsesSchema';

@Injectable({
  providedIn: 'root'
})
export class AnimeService extends DataService {

  routes = {
    [DataType.episodes]: "lastEpisodes",
    [DataType.anime]: "tv",
    [DataType.ovas]: "ovas",
    [DataType.movies]: "movies",
    [DataType.specials]: "specials"

  }

  getData(type: DataType) {

    let route = this.routes[type]
    let extra = ""
    if (type != DataType.episodes)
      extra = "latest/1"


    return this.http.get(`${this.base_url}/${route}/${extra}`).pipe(catchError(this.handleError))
  }

  getAnimeInfo(animeTitle) {
    return this.http.get<InfoResponse>(`${this.base_url}/moreInfo/${animeTitle}`).pipe(catchError(this.handleError))

  }

  searchResult(query) {
    return this.http.get<SearchResponse>(`${this.base_url}/search/${query}`).pipe(catchError(this.handleError))

  }

  getEpisodeServers(id) {
    return this.http.get<ServerResponse>(`${this.base_url}/getAnimeServers/${id}`).pipe(catchError(this.handleError))
  }

}
