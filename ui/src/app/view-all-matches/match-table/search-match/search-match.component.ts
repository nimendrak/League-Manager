import {Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {MatchTableService} from "../../../backend-services/match-table-services/match-table.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatchModel} from "../match.model";

@Component({
  selector: 'app-search-match',
  templateUrl: './search-match.component.html',
  styleUrls: ['./search-match.component.css']
})
export class SearchMatchComponent implements OnInit {
  collapsed = true;

  searchedValue = 'yyyy-MM-dd';

  @Input() isRun;
  @Input() dataSource;
  @Input() matchModels: MatchModel[] = [];

  constructor(private matchTableService: MatchTableService) {
  }

  ngOnInit(): void {
  }

  sendSearchStr() {
    console.log(this.searchedValue);
    const dateReg = /^\d{4}[./-]\d{2}[./-]\d{2}$/;

    if (this.searchedValue.match(dateReg)) {
      return this.matchTableService.getSearchResult(this.searchedValue).subscribe((data: any) => {
        this.matchModels = data;
        // check response
        console.log("search match table");
        console.log(data.response);
        this.dataSource = new MatTableDataSource(data.response);
      }, error => console.error(error));
    }
  }


}
