import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AngularFireModule } from '@angular/fire/compat';
import { RouterTestingModule } from '@angular/router/testing';
import { QuestionService } from 'src/app/Service/question.service';
import { ServiceService } from 'src/app/Service/service.service';
import { environment } from 'src/environments/environment';

import { RequestionComponent } from './requestion.component';

describe('RequestionComponent', () => {
  let component: RequestionComponent;
  let fixture: ComponentFixture<RequestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        AngularFireModule.initializeApp(environment.firebase),
      ],
      declarations: [RequestionComponent],
      providers: [QuestionService, ServiceService],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('Requestions Component creado exitosamente', () => {
    expect(component).toBeTruthy();
  });
});
