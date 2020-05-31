import { TestBed } from '@angular/core/testing';

import { OeuvresService } from './oeuvres.service';

describe('OeuvresService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OeuvresService = TestBed.get(OeuvresService);
    expect(service).toBeTruthy();
  });
});
