import {
  HttpClient,
  HttpClientModule,
  HttpHandler,
} from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { AngularFireModule } from '@angular/fire/compat';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { QuestionService } from '../../Service/question.service';
import { ServiceService } from '../../Service/service.service';
import { environment } from '../../../environments/environment';

import { UpdateComponent } from './update.component';

describe('UpdateComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        AngularFireModule.initializeApp(environment.firebase),
      ],
      declarations: [UpdateComponent],
      providers: [QuestionService, ServiceService, FormBuilder],
    }).compileComponents();
  });

  it('Update Component creado exitosamente', () => {
    const fixture = TestBed.createComponent(UpdateComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
