import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {map} from "rxjs/operators";
import {Match} from "./match";

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private dataPostTestUrl = '/api/matches';

  private matchSortedUrl = '/api/matchesDate';


  constructor(private http: HttpClient) { }

  public getMatchList() {
    return this.http.get<Match[]>(this.dataPostTestUrl).pipe(
      map(response => response)
    );
  }

  public getMatchSortedList() {
    return this.http.get<Match[]>(this.matchSortedUrl).pipe(
      map(response => response)
    );
  }

}
