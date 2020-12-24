import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatchModel} from './match.model';
import {MatchTableService} from "../../backend-services/match-table-services/match-table.service";

@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<MatchModel>;

  // table columns
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['date', 'teamOneName', 'teamOneScore', 'teamTwoName', 'teamTwoScore', 'matchStats'];

  constructor(private matchTableService: MatchTableService) {
  }

  @Input() dataSource;
  @Input() matchModels: MatchModel[] = [];

  ngOnInit() {
    this.matchTableService.getTableData()
      .subscribe((data: any) => {
        this.matchModels = data;
        // check response
        console.log("match table");
        console.log(data.response);
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
  }

  ngAfterViewInit() {
    // this.dataSource.sort = this.sort;
    // this.dataSource.paginator = this.paginator;
    // this.table.dataSource = this.dataSource;
  }

  check() {

  }
}
