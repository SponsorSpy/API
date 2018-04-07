name := """SponsorSpy-API"""

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "42.1.4",
  evolutions,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.avaje.ebeanorm" % "avaje-ebeanorm-agent" % "4.1.4",
  "org.avaje.ebeanorm" % "avaje-ebeanorm" % "7.11.4",
  "com.github.fge" % "json-schema-validator" % "2.2.6",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.4",
  filters
)

resolvers ++= Seq(
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Pablo repo" at "https://raw.github.com/fernandezpablo85/scribe-java/mvn-repo/"
)

routesGenerator := InjectedRoutesGenerator

fork in run := true
