import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from "rxjs/operators";

/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {
  private postRandomMatchUrl = 'http://localhost:4200/api/add/random';
  private getLeaderboardDataUrl = 'http://localhost:4200/api/leaderboard/populate';

  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public sendRandomMatch(): Observable<any> {
    return this.http.post(this.postRandomMatchUrl, {});
  }

  public receiveDataLeaderboard() {
    return this.http.get(this.getLeaderboardDataUrl).pipe(
      map(response => response)
    );
  }
}
