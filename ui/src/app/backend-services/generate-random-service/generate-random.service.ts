import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import {environment} from "../../../environments/environment";

/**
 * Class representing application service.
 *
 * @class RandomMatchService.
 */
@Injectable()
export class RandomMatchService {
  appRoot = environment.API_BASE_URL;

  private postRandomMatchUrl = this.appRoot + '/add/random';

  constructor(private http: HttpClient) {
  }

  /**
   * Makes a http post request to send some data to backend & get response.
   */
  public postRandomMatch(): Observable<any> {
    return this.http.post(this.postRandomMatchUrl, {});
  }

}