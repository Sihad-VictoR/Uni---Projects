import {Component, OnInit} from '@angular/core';
import {FootballclubService} from "../footballclub.service";
import {Footballclub} from "../footballclub";
import {Match} from "../match";
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatchGenerateComponent} from "../match-generate/match-generate.component";
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-football-club',
  templateUrl: './football-club.component.html',
  styleUrls: ['./football-club.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class FootballClubComponent implements OnInit {



  public FootBallClubs: Footballclub[];

  selectedValue : string;
  public newMatch: Match[];

  displayedColumns: string[] = ['Club-Name', 'MP', 'W', 'D','L','GF','GA','GD','Pts'];

  constructor(private footBallClubService: FootballclubService,private snackBar: MatSnackBar,private dialog : MatDialog) {}


  ngOnInit() : void {
    this.footBallClubService.getFootBallClubs().subscribe((data: Footballclub[]) =>{
      console.log(data);
      this.FootBallClubs =data;
    })
  }

  openDialog(){
    this.f1().then(res => this.f2());
  }

  f1() {
    return new Promise((resolve, reject) => {
      this.footBallClubService.randomMatch().subscribe((data: Match[]) =>{
        console.log(data);
        this.newMatch = data;
      })

      setTimeout( () => {
        resolve();
      }, 200);
      // if(this.newMatch == null){
      //   this.openSnackBar();
      //   return;
      // }
    });
  }

  f2() {

    const dialog =this.dialog.open(MatchGenerateComponent,{data: this.newMatch});
    dialog.afterClosed().subscribe(() =>{
      this.ngOnInit();
    })
  }

  onChange() {

    switch(this.selectedValue ){
      case "points":
        this.footBallClubService.getFootBallClubs().subscribe((data: Footballclub[]) =>{
          console.log(data);
          this.FootBallClubs =data;
        })
        break;
      case "wins":
        this.footBallClubService.getWinSortClubs().subscribe((data: Footballclub[]) =>{
          console.log(data);
          this.FootBallClubs =data;
        })
        break;
      case "goals":
        this.footBallClubService.getGoalSortClubs().subscribe((data: Footballclub[]) =>{
          console.log(data);
          this.FootBallClubs =data;
        })
        break;
    }


  }

  openSnackBar(){
    this.snackBar.open('Teams played their matches!!','Dismiss',{duration:2000});
  }


}
