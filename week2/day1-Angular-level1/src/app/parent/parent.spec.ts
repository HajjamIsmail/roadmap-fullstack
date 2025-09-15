import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Parent } from './parent';

describe('Parent', () => {
  let component: Parent;
  let fixture: ComponentFixture<Parent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Parent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Parent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
