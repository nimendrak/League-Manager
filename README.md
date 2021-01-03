## League Manager

The project contains combined an Angular Frontend and Play backend which already configured by a boilerplate. Overview of the League Manager as follows ;

- CLI application has following functions :

    - Add clubs into the League
    - Delete clubs from the League
    - Add matches to the League
    - Show current standings 
    - Search clubs by their name
  

- GUI application has following functions :

    - Add random generated matches into league
    - Sort matches by played date
    - Show current standings
    - Sort standing by wins/ goals scored/ points 
    - Search matches by played date
    

Both CLI and GUI applications updated their DataSources as they update realtime. 

## How to launch the application

- Simply run the following command ```sbt run``` in the root directory to run the GUI application.
- or Run ```app/ConsoleApplication.java```, both Angular client and PlayFramework can be invoked from there as well.

## Essential Directory Layout

    .
    ├── DataSource                           # DataSources for both test and build production
    ├── app                                  # The backend source files
    │   ├── controllers                      # Backend controllers
    │   │   ├── FrontendController.scala     # Asset controller wrapper serving frontend assets and artifacts
    │   │   └── LeagueController.java        # Controller class for return valid responses 
    │   ├── models                           # Backend models
    │   │   ├── FootballClub.java            # Inherited from SportsClub, comparable interface implemented 
    │   │   ├── LeagueManager.java           # Inteface for PremierLeagueManager
    │   │   ├── Match.java                   # Process all the related Match data
    │   │   ├── PremierLeagueManager.java    # LeagueManager interface implemented here
    │   │   ├── ProgressBar.java             # Progression bar for consoleApplication
    │   │   ├── SchoolFootballClub.java      # Inherited from FootballClub
    │   │   ├── SportsClub.java              # Super class for all the clubs, serializable
    │   │   └── UniversityFootballClub.java  # Inherited from FootballClub
    │   ├── services                         # Backend services
    │   │   └── LeagueManagerServices.java   # Controller invokes service class methods
    │   ├── utils                            # Backend Utils
    │   │   └── ApplicationUtil.java         # Create responses from backend
    │   └── ConsoleApplication.java          # Contains main class of the consoleApplication
    ├── conf                                 # Configurations files and other non-compiled resources (on classpath)
    │   ├── application.conf                 # Play application configuratiion file.
    │   ├── logback.xml                      # Logging configuration
    │   └── routes                           # Routes definition file
    ├── logs                                 # Log directory
    ├── project                              # Contains project build configuration and plugins
    │   ├── FrontendCommands.scala           # Frontend build command mapping configuration
    │   ├── FrontendRunHook.scala            # Forntend build PlayRunHook (trigger frontend serve on sbt run)
    │   ├── build.properties                 # Marker for sbt project
    │   └── plugins.sbt                      # SBT plugins declaration
    ├── target                               # Play project build artifact directory
    │   ├── scala-2.13                       # Complied assests
    │   └── streams                          # Complied streams assests   
    ├── test                                 # Contains unit tests of both frontend and backend sources
    ├── ui                                   # Angular frontend source
    │   ├── e2e                              # End to end tests folder
    │   ├── project                          # Contains build properties 
    │   ├── src                              # Source root of the Angular Client
    │   │   ├── app                          # Contains landing page of the web
    │   │   │   ├── abbrev-card              # Abbrevation of the Leaderboard component
    │   │   │   ├── backend-services         # Contains backend services for each componnent
    │   │   │   │   ├── app-service          # Allows communication between AppComp and backend
    │   │   │   │   ├── leaderboard-service  # Allows communication between leaderboard and backend
    │   │   │   │   ├── match-table-service  # Allows communication between match-table and backend
    │   │   │   ├── leaderboard              # Contains standings table component
    │   │   │   ├── random-match-dialog      # Random generated match details component
    │   │   │   ├── view-all-matches         # Contains match table component
    │   │   │   │   ├── match-table          # Contains searched match table component
    │   │   │   │   │   ├── search-match     # Contains the search bar component
    │   │   ├── assets                       # Contains assets of the Angular client
    │   ├── angular.json                     # Angular CLI configuration
    │   ├── karma.conf.js                    # Karma configuration file
    │   ├── package.json                     # NPM package configuration.
    │   ├── tsconfig.json                    # Contains typescript compiler options 
    │   └── tslint.json                      # Lint rules for the ui
    ├── build.sbt                            # Play application SBT configuration
    └── ui-build.sbt                         # SBT command hooks associated with frontend npm scripts 

* Used any of the following [SBT](http://www.scala-sbt.org/) commands which will intern trigger frontend associated npm scripts.

```
    sbt clean           # Clean existing build artifacts

    sbt stage           # Build your application from your project’s source directory

    sbt run             # Run both backend and frontend builds in watch mode

    sbt dist            # Build both backend and frontend sources into a single distribution artifact

    sbt test            # Run both backend and frontend unit tests
```
