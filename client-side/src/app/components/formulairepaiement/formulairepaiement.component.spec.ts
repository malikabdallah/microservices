import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormulairepaiementComponent } from './formulairepaiement.component';

describe('FormulairepaiementComponent', () => {
  let component: FormulairepaiementComponent;
  let fixture: ComponentFixture<FormulairepaiementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormulairepaiementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormulairepaiementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
