import play.api.Play
import play.api.{ Application, GlobalSettings }
import play.Logger
import play.api.mvc.WithFilters
import play.filters.gzip.GzipFilter
import org.geogit.server.core.GeoGitFrontEnd
import akka.actor.{ ActorSystem, Props }
import akka.io.IO
import spray.can.Http
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext.Implicits.global

package object globals {

}

object Global extends WithFilters(new GzipFilter()) with GlobalSettings {

  val frontendPort = ConfigFactory.load().getString("geogit-server-frontend.port")

  val config =
    ConfigFactory.parseString(s"akka.remote.netty.tcp.port=${frontendPort}").withFallback(
      ConfigFactory.parseString("akka.cluster.roles = [frontend]")).
      withFallback(ConfigFactory.load())

  implicit val system = ActorSystem("geogitserver", config)

  override def onStart(app: Application) {
    if (play.api.Play.isDev(play.api.Play.current)) {

      Logger.info("GeoGit Server starting")
      val frontend = system.actorOf(Props[GeoGitFrontEnd], name = "frontend")
      IO(Http) ! Http.Bind(frontend, interface = "0.0.0.0", port = config.getInt("geogit-server.port"))
    }

  }

  override def onStop(app: Application) {
    if (play.api.Play.isDev(play.api.Play.current)) {
      system.shutdown
    }
  }
}

