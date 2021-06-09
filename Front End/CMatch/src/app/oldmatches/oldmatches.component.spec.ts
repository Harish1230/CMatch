import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OldmatchesComponent } from './oldmatches.component';

describe('OldmatchesComponent', () => {
  let component: OldmatchesComponent;
  let fixture: ComponentFixture<OldmatchesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OldmatchesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OldmatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
