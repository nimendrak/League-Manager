import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {environment} from "../../../environments/environment";
import {MatchModel} from "../../view-all-matches/match-table/match.model";

/**
 * Class representing application service.
 *
 * @class LeaderboardService.
 */
@Injectable()
export class MatchTableService {
  appRoot = environment.API_BASE_URL;

  private getTableDataUrl = this.appRoot + '/match-table';
  private getSearchResultUrl = this.appRoot + '/match-table/search';

  constructor(private http: HttpClient) {
  }

  public getTableData(): Observable<MatchModel[]> {
    return this.http.get<MatchModel[]>(this.getTableDataUrl);
  }

  public getSearchResult(date: string): Observable<MatchModel[]> {
    return this.http.get<MatchModel[]>(this.getSearchResultUrl + "/" + date);
  }
}
