import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalViewEntryComponent } from './professional-view-entry.component';

describe('ProfessionalViewEntryComponent', () => {
  let component: ProfessionalViewEntryComponent;
  let fixture: ComponentFixture<ProfessionalViewEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalViewEntryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalViewEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
