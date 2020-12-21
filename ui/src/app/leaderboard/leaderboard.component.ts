import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {ClubModel} from './club.model';

import {DataSource} from "@angular/cdk/collections";
import {Observable} from "rxjs";
import {AppService} from "../app-services/app.service";

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  // @ViewChild(MatTable) table: MatTable<ClubModel[]>;

  // dataSource: ClubModel[];
  collapsed: boolean;

  dataSource;
  clubModel;
  clubModels: ClubModel[];

  dropDownOptionsOne: string[] = [
    // @ts-ignore
    {value: 'goals', viewValue: 'Goals Scored'}, {value: 'wins', viewValue: 'Season Wins'}, {
      value: 'points',
      viewValue: 'Season Points'
    }
  ];
  dropDownOptionsTwo: string[] = [
    // @ts-ignore
    {value: 'ascending', viewValue: 'Ascending'}, {value: 'descending', viewValue: 'Descending'}
  ];

  constructor(private appService: AppService) {
  }

  // /** Columns displayed in the table. */
  displayedColumns = ['Club', 'Location', 'MP', 'W', 'L', 'D', 'GS', 'GR', 'PTS'];

  ngOnInit() {

    this.appService.receiveDataLeaderboard()
      .subscribe((clubModels: ClubModel[]) => {
        this.clubModels = clubModels;

        // check response
        console.log(clubModels.response);
        this.dataSource = new MatTableDataSource(clubModels);
      }, error => console.error(error));

  }

  ngAfterViewInit() {
    // this.dataSource.sort = this.sort;
    // this.dataSource.paginator = this.paginator;
    // this.table.dataSource = this.dataSource;
  }

  // async populateTable() {
  //   const data = await this.appService.receiveDataLeaderboard();
  //   console.log(data.response);
  //   this.dataSource = data.response;
  //
  // }

}


