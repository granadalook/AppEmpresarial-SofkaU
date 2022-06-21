import {
  HttpClient,
  HttpClientModule,
  HttpHandler,
} from '@angular/common/http';
import { InjectionToken } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AngularFireModule } from '@angular/fire/compat';
import { FormBuilder } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule, ToastrService } from 'ngx-toastr';
import { MessageService } from 'primeng/api';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';
import { environment } from 'src/environments/environment';

import { AnswerComponent } from './answer.component';

describe('AnswerComponent', () => {
  let component: AnswerComponent;
  let fixture: ComponentFixture<AnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        ToastrModule.forRoot(),
        AngularFireModule.initializeApp(environment.firebase),
      ],
      declarations: [AnswerComponent],
      providers: [
        FormBuilder,
        MessageService,
        ServiceService,
        QuestionService,
        ToastrService,
        NgbModal,
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    InjectionToken;
  });

  it('Answer Component creado exitosamente', () => {
    const fixture = TestBed.createComponent(AnswerComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
