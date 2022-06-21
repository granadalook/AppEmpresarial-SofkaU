import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Userback } from 'src/app/models/Userback';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UpdateService {
  conexion: boolean = false;
  constructor(private http: HttpClient) {}

  updateUser(update: Userback) {
       return this.http.put<HttpErrorResponse>(
      `${environment.authURL}${environment.update}`,
      update
    );
  }
}
