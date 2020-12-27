import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

import {Observable} from 'rxjs';
import {ClubModel} from "../../leaderboard/club.model";
import {environment} from "../../../environments/environment";

/**
 * Class representing application service.
 *
 * @class LeaderboardService.
 */
@Injectable()
export class LeaderboardService {
  appRoot = environment.API_BASE_URL;

  private getTableDataUrl = this.appRoot + '/leaderboard';
  private getSortingDataUrl = this.appRoot + '/leaderboard';

  constructor(private http: HttpClient) {
  }

  public getTableData(): Observable<ClubModel[]> {
    return this.http.get<ClubModel[]>(this.getTableDataUrl);
  }

  public getSortingData(sort: string, order: string): Observable<ClubModel[]> {
    return this.http.get<ClubModel[]>(this.getSortingDataUrl + "/" + sort + "/" + order);
  }

}
