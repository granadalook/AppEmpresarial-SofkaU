import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { NewUser } from 'src/app/models/newUser';
import { AuthServiceService } from 'src/app/Service/authService/auth-service.service';


@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss'],
  providers: [MessageService],
})
export class RegistroComponent implements OnInit {
  newUser: NewUser;
  mostrar: Boolean = false;
  val1: number = 3;
  userName?: string;
  name?: string;
  password?: string;

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    rating: ['', []],
  });

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private authService: AuthServiceService,
    private route: Router
  ) {
    this.newUser = {
      name: '',
      userName: '',
      password: '',
    };
  }

  ngOnInit(): void {}

  registrar() {
    this.mostrar = !this.mostrar;
    this.authService.loginRegistre(this.newUser).subscribe((res) => {
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
  /* ingresarGoogle() {
    this.mostrar = !this.mostrar;
    this.authService
      .loginGoogle(this.form.value.email, this.form.value.password)
      .then((res) => {
        this.mostrar = !this.mostrar;
      });
  }
  getUserLogged() {
    this.authService.getUserLogged().subscribe((res) => {});
  }

  preguntasHome() {
    this.route.navigate(['preguntas']);
  } */

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

  spinner() {
    this.mostrar = !this.mostrar;
  }
}
