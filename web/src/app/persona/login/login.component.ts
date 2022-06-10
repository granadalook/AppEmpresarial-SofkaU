import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { LoginUser } from 'src/app/models/loginUser';
import { AuthServiceService } from 'src/app/Service/authService/auth-service.service';
import { TokenServiceService } from 'src/app/Service/tokenService/token-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [MessageService],
})
export class LoginComponent implements OnInit {
  mostrar: Boolean = false;
  mostrar2: Boolean = false;
  val1: number = 3;
  displayModal: boolean = false;
  userName?: string;
  password?: string;
  loginUser: LoginUser;
  roles: string[] = [];

  public form: FormGroup = this.formBuilder.group({
    userName: ['', [Validators.required]],
    password: ['', [Validators.required]],
    rating: ['', []],
  });
  public form2: FormGroup = this.formBuilder.group({
    userName: ['', [Validators.required]],
  });

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: AuthServiceService,
    private tokenService: TokenServiceService,
    private route: Router
  ) {
    this.loginUser = {
      userName: 'petrosky',
      password: '33333',
    };
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.roles = this.tokenService.getAuthorities();
    }
  }

  /*  ingresar() {
    this.mostrar = !this.mostrar;
    this.authService
      .login(this.form.value.userName, this.form.value.password)
      .then((res) => {
        if (res == undefined) {
          this.messageService.add({
            severity: 'error',
            summary: 'Rectifique los datos',
            detail: 'Clave o Usuario incorrecto, Intente de Nuevo',
          });
        } else {
          this.messageService.add({
            severity: 'success',
            summary: 'Bienvenido',
            detail: 'Disfruta de tu estadía',
          });
          this.route.navigate(['preguntas']);
        }

        this.mostrar = !this.mostrar;
      });
  }
  ingresarGoogle() {
    this.mostrar = !this.mostrar;
    this.authService
      .loginGoogle(this.form.value.email, this.form.value.password)
      .then((res) => {
        if (res) {
          this.messageService.add({
            severity: 'success',
            summary: 'Bienvenido',
            detail: 'Disfruta de tu estadía',
          });
          setTimeout(() => {
            this.route.navigate(['preguntas']);
          }, 3000);
        } else {
          this.messageService.add({
            severity: 'error',
            summary: 'Rectifique los datos',
            detail: 'Clave o Usuario incorrecto, Intente de Nuevo',
          });
        }
        this.mostrar = !this.mostrar;
      });
  }
  getUserLogged() {
    this.authService.getUserLogged().subscribe((res) => {});
  }

  preguntasHome() {
    this.route.navigate(['preguntas']);
  }
  //TODO: Utilidades
  showSuccess() {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Message Content',
    });
  }
  showInfo() {
    this.messageService.add({
      severity: 'info',
      summary: 'Info',
      detail: 'Message Content',
    });
  }
  showError() {}
  spinner() {
    this.mostrar = !this.mostrar;
  }

  showModalDialog() {
    this.displayModal = true;
  }

  recuperarEmail() {
    try {
      this.mostrar2 = !this.mostrar2;
      this.authService.resetPassword(this.form2.value.email).then((res) => {
        this.displayModal = false;
        this.messageService.add({
          severity: 'success',
          summary: '!Exitoso¡',
          detail: 'Revisa tu bandeja de entrada',
        });
      });
      this.mostrar2 = !this.mostrar2;
    } catch (error) {}
  } */

  onLogin(): void {
    this.authService.login(this.loginUser).subscribe((data) => {
      console.log(data.authorities, data.nombreUsuario, data.token, data.type);
    });
  }
}
