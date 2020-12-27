import {AfterViewInit, ChangeDetectorRef, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {ClubModel} from './club.model';
import {LeaderboardService} from "../backend-services/leaderboard-services/leaderboard.service";

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

  constructor(private leaderboardService: LeaderboardService,
              private changeDetectorRefs: ChangeDetectorRef) {
  }

  // /** Columns displayed in the table. */
  displayedColumns = ['clubName', 'clubLocation', 'numOfMatchesPlayed', 'seasonWins', 'seasonDefeats', 'seasonDraws', 'numOfGoalsScored', 'numOfGoalsReceived', 'numOfPointsGained'];

  @Input() dataSource;
  clubModels: ClubModel[] = [];

  ngOnInit() {
    setInterval(() => this.populateLeaderboardTable(), 500);
    // this.populateLeaderboardTable();
  }

  ngAfterViewInit() {
  }

  onSortAction(p1: string, p2: string) {
    this.leaderboardService.getSortingData(p1, p2)
      .subscribe((data: any) => {
        this.clubModels = data;
        console.log(data.response);
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
  }

  populateLeaderboardTable() {
    this.leaderboardService.getTableData()
      .subscribe((data: any) => {
        this.clubModels = data;
        console.log(data.response);
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
  }


}


