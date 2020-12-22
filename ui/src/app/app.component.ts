import {Component, EventEmitter, Output} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpClient} from "@angular/common/http";
import {RandomMatchService} from "./backend-services/generate-random-service/generate-random.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-client';
  postRequestResponse: string;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private _snackBar: MatSnackBar,
              private http: HttpClient, private randomMatchService: RandomMatchService) {
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

  /**
   * This method is used to test the post request
   */
  public postData(): void {
    this.randomMatchService.postRandomMatch().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    });
    window.location.reload();
  }
}
