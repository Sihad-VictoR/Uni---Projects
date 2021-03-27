import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FootballClubComponent } from '../football-club/football-club.component';

describe('FootballClubComponent', () => {
  let component: FootballClubComponent;
  let fixture: ComponentFixture<FootballClubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FootballClubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FootballClubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
