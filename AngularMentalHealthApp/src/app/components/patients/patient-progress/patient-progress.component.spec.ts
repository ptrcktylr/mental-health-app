import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientProgressComponent } from './patient-progress.component';

describe('PatientProgressComponent', () => {
  let component: PatientProgressComponent;
  let fixture: ComponentFixture<PatientProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientProgressComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
