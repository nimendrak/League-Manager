import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpClient} from '@angular/common/http';
import {AppServices} from './backend-services/app-services/app-services.service';
import {MatchModel} from './view-all-matches/match-table/match.model';
import {RandomMatchDialogComponent} from './random-match-dialog/random-match-dialog.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'angular-client';
  postRequestResponse: string;
  validate: any;

  randomMatchDate: MatchModel;
  @ViewChild('randomMatchDialog') randomMatchDialog: RandomMatchDialogComponent;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private _snackBar: MatSnackBar,
              private http: HttpClient, private navService: AppServices,
              private dialog: MatDialog) {

  }

  ngOnInit(): void {
  }

  ngOnDestroy() {

  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 3000,
    });
  }

  /**
   * This method is used to test the post request
   */
  public generateRandomMatch(): void {
    this.navService.postRandomMatch().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    });
    setTimeout(() => this.toDialog(), 100);
  }

  // calling the dialog form the child component
  toDialog() {
    this.dialog.open(RandomMatchDialogComponent);
    this.dialog.afterAllClosed.subscribe(() =>
      window.location.reload()
    );
  }

  // check whether clubsArray has at least 2 clubs to generate a random match
  validateRandomMatch() {
    this.navService.getClubsArraySize().subscribe((data: any) => {
      this.validate = data.response;
      if (this.validate < 2) {
        this.openSnackBar('Need at least 2 Football Clubs!', 'CLOSE');
      } else {
        this.generateRandomMatch();
      }
    }, error => console.log(error));
  }
}
