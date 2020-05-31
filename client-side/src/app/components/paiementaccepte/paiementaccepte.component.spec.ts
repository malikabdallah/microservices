import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaiementaccepteComponent } from './paiementaccepte.component';

describe('PaiementaccepteComponent', () => {
  let component: PaiementaccepteComponent;
  let fixture: ComponentFixture<PaiementaccepteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaiementaccepteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaiementaccepteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
