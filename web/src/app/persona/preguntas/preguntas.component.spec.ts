import {
  HttpClient,
  HttpClientModule,
  HttpHandler,
} from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { AngularFireModule } from '@angular/fire/compat';
import { RouterTestingModule } from '@angular/router/testing';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';
import { environment } from 'src/environments/environment';
import { PreguntasComponent } from './preguntas.component';

describe('PreguntasComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        AngularFireModule.initializeApp(environment.firebase),
      ],
      declarations: [PreguntasComponent],
      providers: [QuestionService, ServiceService],
    }).compileComponents();
  });

  it('Preguntas Component creado exitosamente', () => {
    const fixture = TestBed.createComponent(PreguntasComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
