import play.api.{ Application, GlobalSettings }
import play.Logger
import play.api.mvc.WithFilters
import play.filters.gzip.GzipFilter
import org.geogit.server.core.GeoGitFrontEnd

object Global extends WithFilters(new GzipFilter()) with GlobalSettings {

  override def onStart(app: Application) {

    Logger.info("GeoGit Server starting")
    GeoGitFrontEnd.main(Seq("2551").toArray)

  }

}
