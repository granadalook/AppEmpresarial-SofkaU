import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from 'src/app/models/Jwt';
import { LoginUser } from 'src/app/models/loginUser';
import { environment } from 'src/environments/environment';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFirestore,
  AngularFirestoreDocument,
} from '@angular/fire/compat/firestore';
import { Router } from '@angular/router';
import firebase from 'firebase/compat/app';
import { User } from '../../models/user';
import { NewUser } from 'src/app/models/newUser';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  userData: any;

  constructor(
    private http: HttpClient,
    public afauth: AngularFireAuth,
    public store: AngularFirestore,
    public router: Router
  ) {
    this.afauth.authState.subscribe((user) => {
      if (user) {
        this.userData = user;
        JSON.parse(localStorage.getItem('user')!);
        localStorage.setItem('user', JSON.stringify(this.userData));
      } else {
        JSON.parse(localStorage.getItem('user')!);
        localStorage.setItem('user', 'null');
      }
    });
  }
  loginRegistre(newUser: NewUser) {
    this.afauth.createUserWithEmailAndPassword(
      newUser.userName,
      newUser.password
    );
    return this.http.post(
      `${environment.authURL}${environment.Create}`,
      newUser
    );
  }
  getUserLogged() {
    throw new Error('Method not implemented.');
  }
  resetPassword(email: any) {
    throw new Error('Method not implemented.');
  }

  login(loginUser: LoginUser): Observable<Jwt> {
    return this.http.post<Jwt>(
      `${environment.authURL}${environment.loginEnd}`,
      loginUser
    );
  }
}
