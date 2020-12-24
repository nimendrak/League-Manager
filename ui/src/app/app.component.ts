import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpClient} from "@angular/common/http";
import {AppServices} from "./backend-services/app-services/app-services.service";
import {MatchModel} from "./view-all-matches/match-table/match.model";
import {RandomMatchDialogComponent} from "./random-match-dialog/random-match-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
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
              private http: HttpClient, private navService: AppServices,
              private dialog: MatDialog) {
  }

  // when application starts, data will be load once
  ngOnInit(): void {
    this.loadData();
  }

  // save data, while closing the tab
  ngOnDestroy() {
    this.saveData();
  }

  /**
   * This method is used to test the post request
   */
  public generateRandomMatch(): void {
    this.navService.postRandomMatch().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    })
  }

  // set a time out till the backend responses
  getRandomDataToDialog() {
    setTimeout(() => this.toDialog(), 10);
  }

  // calling the dialog form the child component
  toDialog() {
    this.dialog.open(RandomMatchDialogComponent);

    this.dialog.afterAllClosed.subscribe(() =>
      window.location.reload());
  }

  saveData() {
    this.navService.postSaveClubsData().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    });

    this.navService.postSaveMatchesData().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    });

    console.log("saved");
  }

  loadData() {
    this.navService.getLoadClubsData().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    });

    this.navService.getLoadMatchesData().subscribe((data: any) => {
      this.postRequestResponse = data.content;
    })
  }
}
