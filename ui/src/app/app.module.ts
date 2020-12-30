import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LeaderboardComponent} from './leaderboard/leaderboard.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatchTableComponent} from './view-all-matches/match-table/match-table.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatInputModule} from '@angular/material/input';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {SearchMatchComponent} from './view-all-matches/match-table/search-match/search-match.component';
import {ViewAllMatchesComponent} from './view-all-matches/view-all-matches.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import {LeaderboardService} from "./backend-services/leaderboard-services/leaderboard.service";
import {AppHttpInterceptorService} from "./backend-services/http-interceptor.service";
import {AppServices} from "./backend-services/app-services/app-services.service";
import {MatchTableService} from "./backend-services/match-table-services/match-table.service";
import {RandomMatchDialogComponent} from './random-match-dialog/random-match-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatCardModule} from "@angular/material/card";
import { CardComponent } from './card/card.component';


const appRoutes: Routes = [
  {path: 'leaderboard', component: LeaderboardComponent},
  {path: 'match-table', component: ViewAllMatchesComponent},
  {path: 'search', component: SearchMatchComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    LeaderboardComponent,
    MatchTableComponent,
    SearchMatchComponent,
    ViewAllMatchesComponent,
    RandomMatchDialogComponent,
    CardComponent
  ],
  entryComponents: [RandomMatchDialogComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatFormFieldModule,
    MatSelectModule,
    MatCardModule,
    ReactiveFormsModule,
    FormsModule,
    MatMenuModule,
    MatInputModule,
    MatSnackBarModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    MatDialogModule
  ],
  providers: [LeaderboardService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService,
    },
    AppServices,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService,
    },
    MatchTableService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService,
    }
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
