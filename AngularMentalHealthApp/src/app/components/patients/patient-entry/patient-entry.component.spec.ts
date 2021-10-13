import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientEntryComponent } from './patient-entry.component';

describe('PatientEntryComponent', () => {
  let component: PatientEntryComponent;
  let fixture: ComponentFixture<PatientEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientEntryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
