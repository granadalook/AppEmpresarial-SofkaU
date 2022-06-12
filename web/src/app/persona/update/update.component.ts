import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

import { NewUser } from 'src/app/models/newUser';
import { UpdateService } from 'src/app/Service/updateService/update.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css'],
  providers: [MessageService],
})
export class UpdateComponent implements OnInit {
  updateUser: NewUser;
  userName?: string;
  password?: string;

  public form: FormGroup = this.formBuilder.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(8)]],
    rating: ['', []],
  });

  constructor(
    private formBuilder: FormBuilder,
    private updateService: UpdateService,
    private messageService: MessageService,
    private route: Router
  ) {
    this.updateUser = {
      userName: '',
      password: '',
    };
  }

  ngOnInit(): void {}
  update() {
    this.updateService.updateUser(this.updateUser).subscribe((res) => {});
    this.messageService.add({
      severity: 'success',
      summary: 'Felicitaciones',
      detail: 'Datos actualizados correctamente',
    });
    setTimeout(() => {
      this.route.navigate(['preguntas']);
    }, 3000);
  }
}
