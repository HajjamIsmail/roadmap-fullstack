import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Twoway } from './twoway';

describe('Twoway', () => {
  let component: Twoway;
  let fixture: ComponentFixture<Twoway>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Twoway]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Twoway);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
