import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewUser } from 'src/app/models/newUser';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UpdateService {
  conexion: boolean = false;
  constructor(private http: HttpClient) {}

  updateUser(update: NewUser) {
    console.log(update);
    return this.http.post(
      `${environment.authURL}${environment.update}`,
      update
    );
  }
}
