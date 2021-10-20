import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalViewProgressComponent } from './professional-view-progress.component';

describe('ProfessionalViewProgressComponent', () => {
  let component: ProfessionalViewProgressComponent;
  let fixture: ComponentFixture<ProfessionalViewProgressComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalViewProgressComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalViewProgressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
