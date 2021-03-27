import { TestBed } from '@angular/core/testing';

import { FootballclubService } from '../footballclub.service';

describe('FootballclubService', () => {
  let service: FootballclubService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FootballclubService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
