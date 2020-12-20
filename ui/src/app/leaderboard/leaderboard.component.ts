import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable} from '@angular/material/table';
import {LeaderboardDataSource} from './leaderboard-datasource';
import {ClubModel} from './club.model';
import {AppService} from "../app.service";

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<ClubModel>;
  dataSource: LeaderboardDataSource;
  collapsed: boolean;

  dropDownOptionsOne: string[] = [
    // @ts-ignore
    {value: 'goals', viewValue: 'Goals Scored'}, {value: 'wins', viewValue: 'Season Wins'}, {value: 'points', viewValue: 'Season Points'}
  ];
  dropDownOptionsTwo: string[] = [
    // @ts-ignore
    {value: 'ascending', viewValue: 'Ascending'}, {value: 'descending', viewValue: 'Descending'}
  ];

  constructor(private readonly appService: AppService) {
    this.appService = appService;
  }

  /** Columns displayed in the table. */
  displayedColumns = ['Club', 'Location', 'MP', 'W', 'L', 'D', 'GS', 'GR', 'PTS'];

  ngOnInit() {
    this.dataSource = new LeaderboardDataSource();
    console.log(this.dataSource);
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

}
