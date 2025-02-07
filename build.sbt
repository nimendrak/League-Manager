import sbt.util.Cache.cache;

name := """angular-play-app"""
organization := "com.nimendra"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.3"

libraryDependencies += guice
