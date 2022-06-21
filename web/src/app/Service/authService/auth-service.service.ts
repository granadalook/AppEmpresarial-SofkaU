import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from 'src/app/models/Jwt';
import { environment } from 'src/environments/environment';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import {
  AngularFirestore,
  AngularFirestoreDocument,
} from '@angular/fire/compat/firestore';
import { Router } from '@angular/router';
import firebase from 'firebase/compat/app';
import { authInstanceFactory } from '@angular/fire/auth/auth.module';
import { Userback } from 'src/app/models/Userback';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  userData: any;

  constructor(
    private http: HttpClient,
    public afauth: AngularFireAuth,
    private auth: AngularFirestore,
    public store: AngularFirestore,
    public router: Router
  ) {
    this.afauth.authState.subscribe((user) => {
      if (user) {
        this.userData = user;
        JSON.parse(sessionStorage.getItem('user')!);
        sessionStorage.setItem('user', JSON.stringify(this.userData));
      } else {
        JSON.parse(sessionStorage.getItem('user')!);
        sessionStorage.setItem('user', 'null');
      }
    });
  }
  getUserByUserName(userName: string) {
    this.http.get(userName);
  }

  loginRegistre(newUser: Userback) {
    this.afauth.createUserWithEmailAndPassword(
      newUser.username,
      newUser.password
    );
    return this.http.post<Userback>(
      `${environment.authURL}${environment.createQuestions}`,
      newUser
    );
  }

  async resetPassword(email: string) {
    try {
      return this.afauth.sendPasswordResetEmail(email);
    } catch (error) {
      console.log(error);
    }
  }

  async loginGoogle(email: string, password: string) {
    try {
      return await this.afauth.signInWithPopup(
        new firebase.auth.GoogleAuthProvider()
      );
    } catch (error) {
      return null;
    }
  }

  login(loginUser: Userback) {
    return this.http.post<Jwt>(
      `${environment.authURL}${environment.loginEnd}`,
      loginUser
    );
  }
}
