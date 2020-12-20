/*
* I am using a ts observable list to populate data in tables
* in order to use that, ClubModel act as a vanilla object
* table: MatTable<ClubModel>;
* */

export class ClubModel {
  public clubName: string;
  public clubLocation: string;
  public numOfMatchesPlayed: number;
  public seasonWins: number;
  public seasonDefeats: number;
  public seasonDraws: number;
  public numOfGoalsReceived: number;
  public numOfGoalsScored: number;
  public numOfPointsGained: number;

  constructor(clubName: string, clubLocation: string, numOfMatchesPlayed: number, seasonWins: number, seasonDefeats: number,
              seasonDraws: number, numOfGoalsReceived: number, numOfGoalsScored: number, numOfPointsGained: number) {
    this.clubName = clubName;
    this.clubLocation = clubLocation;
    this.numOfMatchesPlayed = numOfMatchesPlayed;
    this.seasonWins = seasonWins;
    this.seasonDefeats = seasonDefeats;
    this.seasonDraws = seasonDraws;
    this.numOfGoalsReceived = numOfGoalsReceived;
    this.numOfGoalsScored = numOfGoalsScored;
    this.numOfPointsGained = numOfPointsGained;}
}
