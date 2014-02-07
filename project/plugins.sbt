// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.1")

//Scalastyle plugin
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.3.2")

//Scalariform plugin
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.2.0")

//SBT Assembly plugin
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.10.2")
