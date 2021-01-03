import {Component, Input, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatchTableService} from '../backend-services/match-table-services/match-table.service';
import {ClubModel} from '../leaderboard/club.model';

@Component({
  selector: 'app-view-all-matches',
  templateUrl: './view-all-matches.component.html',
  styleUrls: ['./view-all-matches.component.css']
})
export class ViewAllMatchesComponent implements OnInit {
  collapsed: boolean;

  @Input() dataSource;
  @Input() clubModels: ClubModel[] = [];

  dropDownOptionsOne: string[] = [
    // @ts-ignore
    {value: 'ascending', viewValue: 'Ascending'}, {value: 'descending', viewValue: 'Descending'}
  ];
  selectedProfileOne = this.dropDownOptionsOne[0];

  constructor(private matchTableService: MatchTableService) { }

  ngOnInit(): void {}

  onSortAction(p1: string) {
    this.matchTableService.getSortedTableData(p1)
      .subscribe((data: any) => {
        this.clubModels = data;
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
  }

}
