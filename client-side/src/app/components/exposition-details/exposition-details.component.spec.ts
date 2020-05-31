import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpositionDetailsComponent } from './exposition-details.component';

describe('ExpositionDetailsComponent', () => {
  let component: ExpositionDetailsComponent;
  let fixture: ComponentFixture<ExpositionDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExpositionDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpositionDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
