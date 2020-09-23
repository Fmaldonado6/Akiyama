import { AnimeResponse } from './../../models/api/responses/AnimeResponse';
import { EpisodeResponse } from '../../models/api/responses/EpisodeResponse';
import { EpisodeModel } from './../../models/api/apiContent/EpisodeModel';
import { DataService } from './../data.service';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AnimeService extends DataService {

  getLatestEpisodes() {
    return this.http.get<EpisodeResponse>(`${this.base_url}/lastEpisodes`).pipe(catchError(this.handleError))
  }

  getLatestAnimes() {
    return this.http.get<AnimeResponse>(`${this.base_url}/tv/latest/anime`).pipe(catchError(this.handleError))

  }

}
