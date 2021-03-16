name := """parrotenginev2"""
organization := "com.kodeinc"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

lazy val postgresversion = "9.4-1201-jdbc41"
lazy val slickversion = "3.2.2"
lazy val nimbusversion = "8.20.2"

libraryDependencies ++= Seq(
  jdbc,
  guice,
  evolutions,
  javaJpa,
  ws,
  caffeine,
  "org.hibernate" % "hibernate-core" % "5.4.9.Final",
  "org.postgresql" % "postgresql" %  postgresversion,
  "org.scalactic" %% "scalactic" % slickversion,
  "com.typesafe.slick" %% "slick" % slickversion,
  "com.typesafe.slick" %% "slick-testkit" % slickversion % "test",
  "com.typesafe.slick" %% "slick-hikaricp" % slickversion,
  "com.typesafe.slick" %% "slick-codegen" % slickversion,

  "com.typesafe.play" %% "play-json" % "2.8.0-M5",
  "com.typesafe.play" %% "play-slick" % "4.0.2",
  "org.scalactic" %% "scalactic" % "3.2.0",


  "com.github.tminglei" %% "slick-pg" % "0.19.3",
  "org.scalatest" %% "scalatest-flatspec" % "3.2.0" % "test",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % "test",
  "org.scalatestplus" %% "mockito-3-4" % "3.2.2.0" % "test",

  //Adding JWT TOkenss
  "com.nimbusds" % "nimbus-jose-jwt" % nimbusversion


)

