import {Component, Inject, OnInit} from '@angular/core';
import {MatchModel} from "../view-all-matches/match-table/match.model";
import {RandomMatchService} from "../backend-services/generate-random-service/generate-random.service";

@Component({
  selector: 'app-random-match-dialog',
  templateUrl: './random-match-dialog.component.html',
  styleUrls: ['./random-match-dialog.component.css']
})

// @ts-nocheck
export class RandomMatchDialogComponent implements OnInit {

  randomMatchDate: MatchModel;
  date: string;
  teamOneName: string;
  teamOneScore: number;
  teamTwoName : string;
  teamTwoScore : number;
  matchStats: string;

  constructor(private randomMatchService: RandomMatchService) {

    // getting the response from backend and set it to the dialog box accordingly
    this.randomMatchService.getRandomMatch().subscribe((data: any) => {
      this.randomMatchDate = data.response;
    });
  }

  ngOnInit(): void {
    console.log(this.randomMatchDate);
  }
}
