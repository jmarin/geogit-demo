package controllers

import play.api._
import play.api.mvc._
import play.api.libs.ws.WS
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.Future

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def api(apiUrl: String) = Action.async {
    WS.url("http://localhost/api/" + apiUrl)
      .get()
      .map {
        response =>
          val contentType = response.header("Content-Type").getOrElse("text/plain")
          Ok(response.body).as(contentType)
      }
  }

}
