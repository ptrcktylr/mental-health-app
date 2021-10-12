import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalMyPatientsComponent } from './professional-my-patients.component';

describe('ProfessionalMyPatientsComponent', () => {
  let component: ProfessionalMyPatientsComponent;
  let fixture: ComponentFixture<ProfessionalMyPatientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalMyPatientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalMyPatientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
