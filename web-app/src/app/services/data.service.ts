import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { AppError } from './common/errors/app-error';
import { BadInput } from './common/errors/bad-input';
import { NotFoundError } from './common/errors/not-found-error';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  protected base_url = environment.base_url

  constructor(protected http: HttpClient) { }

  protected handleError(error: Response) {

    if (error.status === 400)
      return throwError(new BadInput(error));

    if (error.status === 404)
      return throwError(new NotFoundError(error));

    return throwError(new AppError(error));
  }

}
