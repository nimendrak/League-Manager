import {Component, Inject, OnInit} from '@angular/core';
import {MatchModel} from "../view-all-matches/match-table/match.model";
import {AppServices} from "../backend-services/app-services/app-services.service";

@Component({
  selector: 'app-random-match-dialog',
  templateUrl: './random-match-dialog.component.html',
  styleUrls: ['./random-match-dialog.component.css']
})

export class RandomMatchDialogComponent implements OnInit {

  randomMatchDate: MatchModel;
  date: string;
  teamOneName: string;
  teamOneScore: number;
  teamTwoName : string;
  teamTwoScore : number;
  matchStats: string;

  constructor(private randomMatchService: AppServices) {

    // getting the response from backend and set it to the dialog box params
    this.randomMatchService.getRandomMatch().subscribe((data: any) => {
      this.randomMatchDate = data.response;
    });
  }

  ngOnInit(): void {
  }
}
