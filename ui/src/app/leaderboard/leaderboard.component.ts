import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {ClubModel} from './club.model';
import {AppService} from "../app-services/app.service";

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<ClubModel[]>;

  collapsed: boolean;
  dropdownOne: boolean;
  dropdownTwo: boolean;

  dropDownOptionsOne: string[] = [
    // @ts-ignore
    {value: 'goals', viewValue: 'Goals Scored'}, {value: 'wins', viewValue: 'Season Wins'}, {
      value: 'points',
      viewValue: 'Season Points'
    }
  ];
  selectedProfileOne = this.dropDownOptionsOne[0];

  dropDownOptionsTwo: string[] = [
    // @ts-ignore
    {value: 'ascending', viewValue: 'Ascending'}, {value: 'descending', viewValue: 'Descending'}
  ];
  selectedProfileTwo = this.dropDownOptionsTwo[0];

  constructor(private appService: AppService) {
  }

  // /** Columns displayed in the table. */
  displayedColumns = ['clubName', 'clubLocation', 'numOfMatchesPlayed', 'seasonWins', 'seasonDefeats', 'seasonDraws', 'numOfGoalsScored', 'numOfGoalsReceived', 'numOfPointsGained'];

  dataSource;
  clubModels: ClubModel[] = [];
  selectedValueOne: any;

  ngOnInit() {
    this.appService.receiveDataLeaderboard()
      .subscribe((data: any) => {
        this.clubModels = data;
        // check response
        console.log(data.response);
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
  }

  ngAfterViewInit() {
    // this.dataSource.sort = this.sort;
    // this.dataSource.paginator = this.paginator;
  }

  onSortAction(p1, p2) {
    console.log(p1, p2);
  }



}


