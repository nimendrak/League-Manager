import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpClient} from "@angular/common/http";
import {RandomMatchService} from "./backend-services/generate-random-service/generate-random.service";
import {MatchModel} from "./view-all-matches/match-table/match.model";
import {RandomMatchDialogComponent} from "./random-match-dialog/random-match-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-client';
  postRequestResponse: string;

  randomMatchDate: MatchModel;
  @ViewChild("randomMatchDialog") randomMatchDialog: RandomMatchDialogComponent;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private _snackBar: MatSnackBar,
              private http: HttpClient, private randomMatchService: RandomMatchService,
              private dialog: MatDialog) {
  }

  /**
   * This method is used to test the post request
   */
  public generateRandomMatch(): void {
    this.randomMatchService.postRandomMatch().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    })
  }

  // set a time out till the backend responses
  getRandomDataToDialog() {
    setTimeout(()=> this.toDialog(), 10);
  }

  // calling the dialog form the child component
  toDialog() {
    this.dialog.open(RandomMatchDialogComponent);

    this.dialog.afterAllClosed.subscribe(() =>
      window.location.reload());
  }
}
