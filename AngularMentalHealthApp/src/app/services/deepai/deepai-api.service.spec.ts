import { TestBed } from '@angular/core/testing';

import { DeepaiApiService } from './deepai-api.service';

describe('DeepaiApiService', () => {
  let service: DeepaiApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeepaiApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
