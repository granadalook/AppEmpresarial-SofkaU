import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
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
  [x: string]: any;

  userName = new FormControl('');

  constructor(
    private authSvc: AuthServiceService,
    private messageService: MessageService,
    private route: Router
  ) {}

  ngOnInit(): void {}

  onReset() {
    try {
      const email = this.userName.value;
      this.authSvc.resetPassword(email);
      window.alert('Email enviado, revisa tu bandeja de correo!');
      this.route.navigate(['/login']);
    } catch (error) {
      console.log(error);
    }
  }
}
