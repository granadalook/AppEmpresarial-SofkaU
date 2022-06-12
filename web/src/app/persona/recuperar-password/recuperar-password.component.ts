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
  [x: string]: any;
  email: string = '';
  enviado: boolean = true;
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
    try {
      this.authSvc.resetPassword(this.email);
      this.enviado = false;
      setTimeout(() => {
        this.route.navigate(['/update']);
      }, 5000);
    } catch (error) {
      console.log(error);
    }
  }
}
