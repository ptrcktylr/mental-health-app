import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalAddPatientComponent } from './professional-add-patient.component';

describe('ProfessionalAddPatientComponent', () => {
  let component: ProfessionalAddPatientComponent;
  let fixture: ComponentFixture<ProfessionalAddPatientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalAddPatientComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalAddPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
