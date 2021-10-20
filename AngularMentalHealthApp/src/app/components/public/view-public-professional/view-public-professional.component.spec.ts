import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPublicProfessionalComponent } from './view-public-professional.component';

describe('ViewPublicProfessionalComponent', () => {
  let component: ViewPublicProfessionalComponent;
  let fixture: ComponentFixture<ViewPublicProfessionalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPublicProfessionalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPublicProfessionalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
