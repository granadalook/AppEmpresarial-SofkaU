import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from 'src/app/models/Jwt';
import { LoginUser } from 'src/app/models/loginUser';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  constructor(private http: HttpClient) {}

  login(loginUser: LoginUser): Observable<Jwt> {
    return this.http.post<Jwt>(
      `${environment.authURL}${environment.loginEnd}`,
      loginUser
    );
  }
}
