import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AngularFireModule, FIREBASE_OPTIONS } from '@angular/fire/compat';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { environment } from 'src/environments/environment';
import { QuestionComponent } from '../paginas/question/question.component';
import { QuestionService } from '../Service/question.service';
import { ServiceService } from '../Service/service.service';
import { TokenServiceService } from '../Service/tokenService/token-service.service';

import { NavbarComponent } from './navbar.component';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientModule,
        AngularFireModule.initializeApp(environment.firebase),
      ],
      declarations: [NavbarComponent],
      providers: [QuestionService, ServiceService],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('NavBar Component creado exitosamente', () => {
    const fixtur = TestBed.createComponent(NavbarComponent);
    const app = fixtur.componentInstance;
    expect(app).toBeTruthy();
  });
});
