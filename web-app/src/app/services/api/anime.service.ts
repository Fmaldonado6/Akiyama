import { EpisodeResponse } from './../../models/api/EpisodeResponse';
import { EpisodeModel } from './../../models/api/EpisodeModel';
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

}
