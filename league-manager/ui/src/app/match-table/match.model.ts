/*
* I am using a ts observable list to populate data in tables
* in order to use that, MatchModel act as a vanilla object
* table: MatTable<MatchModel>
* */

export class MatchModel {
  public dateString: string;
  public clubName: string;
  public stats: string;
  public goalsReceived: number;
  public goalsScored: number;
  public matchPoints: number;

  constructor(dateString: string, clubName: string, stats: string, goalsReceived: number, goalsScored: number, points: number) {
    this.dateString = dateString;
    this.clubName = clubName;
    this.stats = stats;
    this.goalsReceived = goalsReceived;
    this.goalsScored = goalsScored;
    this.matchPoints = points;
  }
}
