import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTable, MatTableDataSource} from '@angular/material/table';
import {MatchModel} from './match.model';
import {MatchTableService} from '../../backend-services/match-table-services/match-table.service';

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
    this.populateMatchTableData();
    // setInterval(() => this.populateMatchTableData(), 500);
  }

  ngAfterViewInit() {
  }

  populateMatchTableData() {
    this.matchTableService.getTableData()
      .subscribe((data: any) => {
        this.matchModels = data;
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
  }
}
