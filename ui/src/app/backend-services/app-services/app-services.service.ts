import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {environment} from "../../../environments/environment";

/**
 * Class representing application service.
 *
 * @class AppServices.
 */
@Injectable()
export class AppServices {
  appRoot = environment.API_BASE_URL;

  private postRandomMatchUrl = this.appRoot + '/add/random';
  private getRandomMatchUrl = this.appRoot + '/add/get-random';

  private postSaveClubsDataUrl = this.appRoot + '/save-data/clubs';
  private postSaveMatchesDataUrl = this.appRoot + '/save-data/matches';

  private postLoadClubsDataUrl = this.appRoot + '/load-data/clubs';
  private postLoadMatchesDataUrl = this.appRoot + '/load-data/matches';

  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public postRandomMatch(): Observable<any> {
    return this.http.post(this.postRandomMatchUrl, {});
  }

  public getRandomMatch(): Observable<any> {
    return this.http.get(this.getRandomMatchUrl);
  }

  public postSaveClubsData() {
    return this.http.post(this.postSaveClubsDataUrl, {});
  }

  public postSaveMatchesData() {
    return this.http.post(this.postSaveMatchesDataUrl, {});
  }

  public getLoadClubsData() {
    return this.http.get(this.postLoadClubsDataUrl);
  }

  public getLoadMatchesData() {
    return this.http.get(this.postLoadMatchesDataUrl);
  }


}
