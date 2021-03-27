import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {Footballclub} from "./footballclub";
import {map} from "rxjs/operators";
import {Match} from "./match";


@Injectable({
  providedIn: 'root'
})
export class FootballclubService {
  private serviceUrl = '/api/footballClubs';

  private goalSortUrl = '/api/clubGoalSort';

  private winSortUrl = '/api/clubWinSort';

  private randomMatchUrl = '/api/randomMatch';

  constructor(private http: HttpClient) { }


  public getFootBallClubs() {
    return this.http.get<Footballclub[]>(this.serviceUrl);
  }

  public getWinSortClubs() {
    return this.http.get<Footballclub[]>(this.winSortUrl);
  }

  public getGoalSortClubs() {
    return this.http.get<Footballclub[]>(this.goalSortUrl);
  }

  public randomMatch() {
    return this.http.get<Match[]>(this.randomMatchUrl).pipe(
      map(response => response)
    );
  }
}
