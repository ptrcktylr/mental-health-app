import { TestBed } from '@angular/core/testing';

import { SentpackService } from './sentpack.service';

describe('SentpackService', () => {
  let service: SentpackService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SentpackService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
