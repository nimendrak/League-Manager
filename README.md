## League Manager

- The project contains combined Angular frontend and Play backend which already configured by a boilerplate.

## How to launch the application

Simply run the following command ```sbt run``` in the root directory.

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
    ├── lib                                  # Contains JUnit jar files   
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
