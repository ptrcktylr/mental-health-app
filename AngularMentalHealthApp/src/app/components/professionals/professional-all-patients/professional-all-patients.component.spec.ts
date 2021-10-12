import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalAllPatientsComponent } from './professional-all-patients.component';

describe('ProfessionalAllPatientsComponent', () => {
  let component: ProfessionalAllPatientsComponent;
  let fixture: ComponentFixture<ProfessionalAllPatientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalAllPatientsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalAllPatientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
