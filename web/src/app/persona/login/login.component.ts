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
  change: boolean = true;
  username?: string;
  password?: string;
  loginUser: Userback;
  roles: string[] = [];
  errMsj: string = '';

  public form: FormGroup = this.formBuilder.group({
    username: ['', [Validators.email]],
    password: ['', [Validators.required]],
  });
  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: AuthServiceService,
    private tokenService: TokenServiceService,
    private route: Router
  ) {
    this.loginUser = {
      username: '',
      password: '',
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
    this.authService.login(this.loginUser).subscribe(
      (response) => {
        this.change = false;
        this.isLogged = true;
        this.tokenService.setUserName(response.username);
        this.tokenService.setToken(response.token);
        this.messageService.add({
          severity: 'success',
          summary: 'Bienvenido ' + response.username,
          detail: 'Gracias por visitarnos',
        }),
          setTimeout(() => {
            this.route.navigate(['preguntas']);
          }, 3000);
      },
      (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Rectifique los datos',
          detail: 'Clave o Usuario incorrecto, Intente de Nuevo',
        });
      }
    );
  }
}
