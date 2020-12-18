/*
* I am using a ts observable list to populate data in tables
* in order to use that, ClubModel act as a vanilla object
* table: MatTable<ClubModel>;
* */

export class ClubModel {
  public clubName: string;
  public clubLocation: string;
  public seasonWins: number;
  public seasonDefeats: number;
  public seasonDraws: number;
  public numOfGoalsReceived: number;
  public numOfGoalsScored: number;
  public numOfPointsGained: number;
  public numOfMatchesPlayed: number;

  constructor(clubName: string, clubLocation: string, seasonWins: number, seasonDefeats: number, seasonDraws: number,
              numOfGoalsReceived: number, numOfGoalsScored: number, numOfPointsGained: number, numOfMatchesPlayed: number) {
    this.clubName = clubName;
    this.clubLocation = clubLocation;
    this.seasonWins = seasonWins;
    this.seasonDefeats = seasonDefeats;
    this.seasonDraws = seasonDraws;
    this.numOfGoalsReceived = numOfGoalsReceived;
    this.numOfGoalsScored = numOfGoalsScored;
    this.numOfPointsGained = numOfPointsGained;
    this.numOfMatchesPlayed = numOfMatchesPlayed;
  }
}
