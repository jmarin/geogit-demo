name := "geogit-demo"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.3"

scalariformSettings

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  filters
)     

lazy val geoGitServer =
  ProjectRef(uri("ssh://git@github.com/boundlessgeo/geogit-server.git"), "geogit-server")

lazy val geogitDemo = project.in(file("."))
  .aggregate(geoGitServer)
  .dependsOn(geoGitServer)

play.Project.playScalaSettings

JavaScriptBuild.javaScriptUiSettings


