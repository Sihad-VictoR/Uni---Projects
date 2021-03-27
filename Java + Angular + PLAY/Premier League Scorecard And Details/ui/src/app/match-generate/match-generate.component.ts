import { Component, OnInit,Inject } from '@angular/core';
import {Match} from "../match";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-match-generate',
  templateUrl: './match-generate.component.html',
  styleUrls: ['./match-generate.component.css']
})
export class MatchGenerateComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public newMatch:Match[]) {
  }

  ngOnInit(): void {
  }

}
