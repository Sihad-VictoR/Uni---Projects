import {Component, OnInit} from '@angular/core';

import { Match } from '../match';
import {MatchService} from "../match.service";
import {Subject} from 'rxjs';

@Component({
  selector: 'app-match-history',
  templateUrl: './match-history.component.html',
  styleUrls: ['./match-history.component.css']
})
export class MatchHistoryComponent implements OnInit {

  public Matches : Match[];
  public  newList : Match[];

  public sortedMatchList : Match[];

  DateString: string  ;
  DateSelected: Date ;

  displayedColumns: string[] = ['Home Club', 'Home Score', 'Date', 'Away Score','Away Club'];

  public newMatch: Match[];



  constructor(private matchService: MatchService) { }

  ngOnInit(): void {
    this.matchService.getMatchList().subscribe((data: Match[]) =>{
      console.log(data);
      this.Matches = data;
    })

    this.matchService.getMatchSortedList().subscribe((data: Match[]) =>{
      console.log(data);
      this.sortedMatchList = data;
    })
  }

  applyFilter(){
    this.DateString = this.DateSelected + "T00:00:00.000+0000";
    this.DateSelected = new Date(this.DateString) ;
    this.newList=this.Matches.filter(value => value.matchDate === (this.DateString) )
    this.Matches = this.newList;
  }

  getSortedList(){
    this.Matches =this.sortedMatchList;
  }

}


