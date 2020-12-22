/*
* I am using a ts observable list to populate data in tables
* in order to use that, MatchModel act as a vanilla object
* table: MatTable<MatchModel>
* */

export interface MatchModel {
  date: string,
  teamScore: number;

  clubOne: {
    clubName: string,
    stats: string,
    numOfGoalsReceived: number,
    numOfGoalsScored: number,
    numOfPointsGained: number
  }
  clubTwo: {
    clubName: string,
    stats: string,
    numOfGoalsReceived: number,
    numOfGoalsScored: number,
    numOfPointsGained: number
  }

}
