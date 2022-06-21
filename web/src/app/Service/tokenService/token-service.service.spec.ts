import { TestBed } from '@angular/core/testing';

import { TokenServiceService } from './token-service.service';

describe('TokenServiceService', () => {
  let service: TokenServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TokenServiceService);
  });

  it('TokenService Component creado exitosamente', () => {
    expect(service).toBeTruthy();
  });
});
