import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsulteroeuvresComponent } from './consulteroeuvres.component';

describe('ConsulteroeuvresComponent', () => {
  let component: ConsulteroeuvresComponent;
  let fixture: ComponentFixture<ConsulteroeuvresComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsulteroeuvresComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsulteroeuvresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
