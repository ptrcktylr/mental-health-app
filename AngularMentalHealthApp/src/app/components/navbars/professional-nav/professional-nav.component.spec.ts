import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessionalNavComponent } from './professional-nav.component';

describe('ProfessionalNavComponent', () => {
  let component: ProfessionalNavComponent;
  let fixture: ComponentFixture<ProfessionalNavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfessionalNavComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessionalNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
