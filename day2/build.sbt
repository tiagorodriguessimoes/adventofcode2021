ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

val fs2version = "3.2.4"
lazy val dependencies = Seq(
  "co.fs2" %% "fs2-core" % fs2version,
  "co.fs2" %% "fs2-io" % fs2version
)

lazy val root = (project in file("."))
  .settings(
    name := "day2",
    idePackagePrefix := Some("com.github.tiagorodriguessimoes")
  ).settings(
    libraryDependencies ++= dependencies
  )

