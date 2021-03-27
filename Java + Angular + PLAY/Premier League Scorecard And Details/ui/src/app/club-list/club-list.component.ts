import { Component, OnInit } from '@angular/core';
import {FootballclubService} from "../footballclub.service";
import {Footballclub} from "../footballclub";

@Component({
  selector: 'app-club-list',
  templateUrl: './club-list.component.html',
  styleUrls: ['./club-list.component.css']
})
export class ClubListComponent implements OnInit {

  public FootBallClubs : Footballclub[];

  constructor(private footBallClubService: FootballclubService) { }

  ngOnInit(): void {
    this.footBallClubService.getFootBallClubs().subscribe((data: Footballclub[]) =>{
      console.log(data);
      this.FootBallClubs =data;
    })
  }

}
