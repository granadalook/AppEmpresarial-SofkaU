import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServiceService } from '../Service/service.service';
import { TokenServiceService } from '../Service/tokenService/token-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  userLogged = this.authService.getUserLogged();
  disabled: boolean = false;
  islogged: boolean = false;

  constructor(
    private authService: ServiceService,
    private route: Router,
    private tokenService: TokenServiceService
  ) {}

  ngOnInit(): void {
    this.traerdatos();
    if (this.tokenService.getToken()) {
      this.islogged = true;
    } else {
      this.islogged = false;
    }
  }

  traerdatos() {
    this.userLogged.subscribe((value) => {
      if (value?.email == undefined) {
        this.disabled = true;
      } else {
        this.disabled = false;
      }
    });
  }

  login() {
    this.route.navigate(['login']);
  }

  offlogin() {
    this.tokenService.logOut();
    window.location.reload();
  }
}
