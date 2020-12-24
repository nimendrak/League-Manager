/*
* I am using a ts observable list to populate data in tables
* in order to use that, MatchModel act as a vanilla object
* table: MatTable<MatchModel>
* */

export interface MatchModel {
  date: string;
  teamOneName: string;
  teamOneScore: number;
  teamTwoName: string;
  teamTwoScore: number;
  matchStats: string;
}
