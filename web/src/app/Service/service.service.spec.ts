import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { AngularFireModule } from '@angular/fire/compat';
import { RouterTestingModule } from '@angular/router/testing';
import { environment } from 'src/environments/environment';
import { QuestionService } from './question.service';

import { ServiceService } from './service.service';

describe('ServiceService', () => {
  let service: ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        AngularFireModule.initializeApp(environment.firebase),
      ],
    });
    service = TestBed.inject(ServiceService);
  });

  it('ServiceService Component creado exitosamente', () => {
    expect(service).toBeTruthy();
  });
});
