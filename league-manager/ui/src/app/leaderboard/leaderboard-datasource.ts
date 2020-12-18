import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';
import { ClubModel } from './club.model';

// data source
const DATA: ClubModel[] = [
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
  1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
  new ClubModel ('Hydrogen', 'Galle', 1, 1, 1,
    1, 10, 10, 20),
];

/**
 * Data source for the Leaderboard view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class LeaderboardDataSource extends DataSource<ClubModel> {
  data: ClubModel[] = DATA;
  paginator: MatPaginator;
  sort: MatSort;

  constructor() {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<ClubModel[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    const dataMutations = [
      observableOf(this.data),
      this.paginator.page,
      this.sort.sortChange
    ];

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: ClubModel[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: ClubModel[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'W': return compare(a.seasonWins, b.seasonWins, isAsc);
        case 'GS': return compare(a.numOfGoalsScored, b.numOfGoalsScored, isAsc);
        case 'PTS': return compare(+a.numOfPointsGained, +b.numOfPointsGained, isAsc);
        default: return 0;
      }
    });
  }

  // connect to the backend
  // tslint:disable-next-line:typedef
  private populateClubData(data: ClubModel[]) {

  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a: string | number, b: string | number, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
