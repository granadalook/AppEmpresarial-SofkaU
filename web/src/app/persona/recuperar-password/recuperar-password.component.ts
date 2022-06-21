import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthServiceService } from 'src/app/Service/authService/auth-service.service';

@Component({
  selector: 'app-recuperar-password',
  templateUrl: './recuperar-password.component.html',
  styleUrls: ['./recuperar-password.component.css'],
  providers: [MessageService],
})
export class RecuperarPasswordComponent implements OnInit {
  email: string = '';

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    rating: ['', []],
  });

  constructor(
    private formBuilder: FormBuilder,
    private authSvc: AuthServiceService,
    private messageService: MessageService,
    private route: Router
  ) {}

  ngOnInit(): void {}

  onReset() {
    if (this.email) {
      this.authSvc.resetPassword(this.email);
      this.messageService.add({
        severity: 'success',
        summary: 'Email de recuperacion enviado',
        detail:
          'Revisa tu vandeja de entrada en ' +
          this.email +
          ' y actualiza tus datos',
      });
      setTimeout(() => {
        this.route.navigate(['/update']);
      }, 5000);
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Rectifique los datos',
        detail: 'Error al recuperar los datos intente de nuevo',
      });
    }
  }
}
