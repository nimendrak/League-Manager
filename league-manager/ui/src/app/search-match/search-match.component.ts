import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-match',
  templateUrl: './search-match.component.html',
  styleUrls: ['./search-match.component.css']
})
export class SearchMatchComponent implements OnInit {
  collapsed = true;
  value = 'Search something...';

  constructor() { }

  ngOnInit(): void {
  }

}
