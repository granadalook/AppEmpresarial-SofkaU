import { HttpClient, HttpHandler } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { UpdateService } from './update.service';

describe('UpdateService', () => {
  let service: UpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({ providers: [HttpClient, HttpHandler] });
    service = TestBed.inject(UpdateService);
  });

  it('Update Component creado exitosamente', () => {
    expect(service).toBeTruthy();
  });
});
