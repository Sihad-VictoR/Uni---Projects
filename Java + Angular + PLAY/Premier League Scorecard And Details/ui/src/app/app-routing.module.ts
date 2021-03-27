import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FootballClubComponent } from './football-club/football-club.component';
import { MatchHistoryComponent } from './match-history/match-history.component';
import {ClubListComponent} from "./club-list/club-list.component";

const routes: Routes = [
  { path:'', redirectTo:'/footballclubs' , pathMatch: 'full'},
  { path: 'footballclubs' , component: FootballClubComponent },
  { path: 'matches' , component: MatchHistoryComponent},
  { path: 'clubList' , component: ClubListComponent},
  { path: "**", component: FootballClubComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [FootballClubComponent, MatchHistoryComponent]
