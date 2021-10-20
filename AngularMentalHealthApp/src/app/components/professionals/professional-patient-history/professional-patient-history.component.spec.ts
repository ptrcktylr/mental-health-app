import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalPatientHistoryComponent } from './professional-patient-history.component';

describe('ProfessionalPatientHistoryComponent', () => {
  let component: ProfessionalPatientHistoryComponent;
  let fixture: ComponentFixture<ProfessionalPatientHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalPatientHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalPatientHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
