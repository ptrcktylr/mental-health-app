import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPublicEntryComponent } from './view-public-entry.component';

describe('ViewPublicEntryComponent', () => {
  let component: ViewPublicEntryComponent;
  let fixture: ComponentFixture<ViewPublicEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPublicEntryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPublicEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
