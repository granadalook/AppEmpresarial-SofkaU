import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService, Message } from 'primeng/api';
import { Userback } from 'src/app/models/Userback';
import { AuthServiceService } from 'src/app/Service/authService/auth-service.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss'],
  providers: [MessageService],
})
export class RegistroComponent implements OnInit {
  newUser: Userback;
  val1: number = 3;
  userName?: string;
  password?: string;
  status?: number;

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
      userName: '',
      password: '',
    };
  }

  ngOnInit(): void {}

  registrar() {
    this.authService.loginRegistre(this.newUser).subscribe(
      (res) => {},
      (err) => {
        console.log('status error--->' + err.status);
        this.status = err.status;
        if (this.status === 200) {
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
          setTimeout(() => {}, 3000);
        }
      }
    );
  }
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
}
