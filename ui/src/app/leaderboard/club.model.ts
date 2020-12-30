/*
* I am using a ts observable list to populate data in tables
* in order to use that, ClubModel act as a vanilla object
* table: MatTable<ClubModel>;
* */

export interface ClubModel {
  clubName: string;
  clubLocation: string;
  numOfMatchesPlayed: number;
  seasonWins: number;
  seasonDefeats: number;
  seasonDraws: number;
  goalsDifference: number;
  numOfGoalsScored: number;
  numOfPointsGained: number;
}
