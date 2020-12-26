import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatchModel} from './match.model';
import {MatchTableService} from "../../backend-services/match-table-services/match-table.service";

@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent implements AfterViewInit, OnInit {
  paginator: MatPaginator;

  table: MatTable<MatchModel>;

  // table columns
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['date', 'teamOneName', 'teamOneScore', 'teamTwoName', 'teamTwoScore', 'matchStats'];

  constructor(private matchTableService: MatchTableService) {
  }

  @Input() dataSource;
  @Input() matchModels: MatchModel[] = [];

  ngOnInit() {
    setInterval(() => this.populateMatchTableData(), 100);
  }

  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
    // this.table.dataSource = this.dataSource;
  }

  populateMatchTableData() {
    this.matchTableService.getTableData()
      .subscribe((data: any) => {
        this.matchModels = data;
        // check response
        // console.log("match table");
        // console.log(data.response);
        this.dataSource = new MatTableDataSource<MatchModel>(data.response);
      }, error => console.error(error));
  }

}
