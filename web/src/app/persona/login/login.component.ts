import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { Userback } from 'src/app/models/Userback';
import { AuthServiceService } from 'src/app/Service/authService/auth-service.service';
import { TokenServiceService } from 'src/app/Service/tokenService/token-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [MessageService],
})
export class LoginComponent implements OnInit {
  isLogged: boolean = false;
  isLoginFail: boolean = false;
  displayModal: boolean = false;
  username?: string;
  password?: string;
  loginUser: Userback;
  roles: string[] = [];
  errMsj: string = '';

  public form: FormGroup = this.formBuilder.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
    rating: ['', []],
  });
  public form2: FormGroup = this.formBuilder.group({
    username: ['', [Validators.required]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: AuthServiceService,
    private tokenService: TokenServiceService,
    private route: Router
  ) {
    this.loginUser = {
      username: 'user',
      password: 'user',
    };
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.isLoginFail = false;
      this.roles = this.tokenService.getAuthorities();
    }
  }
  Registrarme() {
    this.route.navigate(['registro']);
  }
  ClickAqui() {
    this.route.navigate(['reset']);
  }

  navegar() {
    this.route.navigate(['preguntas']);
  }

  ingresar() {
    let parce = JSON.stringify(this.loginUser);
    
    this.authService.login(this.loginUser).subscribe(
      (data) => {
        this.isLogged = true;
        //this.tokenService.setUserName(data.userName);
        // this.tokenService.setAuthorities(data.authorities);
        this.tokenService.setToken(data.token);
        //this.roles = data.authorities;
        this.messageService.add({
          severity: 'success',
          summary: 'Bienvenido ' + data.userName,
          detail: 'Gracias por visitarnos',
        }),
          setTimeout(() => {
            this.route.navigate(['preguntas']);
          }, 3000);
      },
      (err) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Rectifique los datos',
          detail: 'Clave o Usuario incorrecto, Intente de Nuevo',
        });
        setTimeout(() => {}, 3000);
      }
    );
  }
}
