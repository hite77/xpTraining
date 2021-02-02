import { TestBed } from '@angular/core/testing';

import { RockpaperscissorsService } from './rockpaperscissors.service';

describe('RockpaperscissorsService', () => {
  let service: RockpaperscissorsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RockpaperscissorsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
