import {Component, OnInit, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-search-match',
  templateUrl: './search-match.component.html',
  styleUrls: ['./search-match.component.css']
})
export class SearchMatchComponent implements OnInit {
  collapsed = true;

  searchedValue = 'yyyy-MM-dd';
  searchBtnStat = false;

  @Output() sendSearchStrEvent = new EventEmitter<String>();
  @Output() sendSearchBtnEvent = new EventEmitter<Boolean>();

  constructor() { }

  ngOnInit(): void {
  }

  sendSearchStr() {
    return this.sendSearchStrEvent.emit(this.searchedValue);
  }

  sendBtnStat () {
    return this.sendSearchBtnEvent.emit(this.searchBtnStat);
  }
}
