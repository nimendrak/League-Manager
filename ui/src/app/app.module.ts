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
import {MatchTableComponent} from './match-table/match-table.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatInputModule} from '@angular/material/input';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {SearchMatchComponent} from './search-match/search-match.component';
import {ViewAllMatchesComponent} from './view-all-matches/view-all-matches.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";

import {AppService} from './app.service';
import {AppHttpInterceptorService} from './http-interceptor.service';

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
  ],
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
    })
  ],
  providers: [AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService,
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
