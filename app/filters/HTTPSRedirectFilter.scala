package global.filters

import javax.inject.Inject

import akka.stream.Materializer

import scala.concurrent._

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

/**
  * Simple helper class to redirect all incoming requests to use HTTPS assuming the x-forwarded-proto header is configured
  * properly.
  */

class HTTPSRedirectFilter @Inject() (implicit val mat: Materializer) extends Filter {

  def apply(nextFilter: (RequestHeader) => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    //play uses lower case headers.
    requestHeader.headers.get("x-forwarded-proto") match {
      case Some(header) => {
        if ("https" == header) {
          nextFilter(requestHeader).map { result =>
            result.withHeaders(("Strict-Transport-Security", "max-age=31536000"))
          }
        } else {
          Future.successful(Results.Redirect("https://" + requestHeader.host + requestHeader.uri, 301))
        }
      }
      case _ => nextFilter(requestHeader)
    }
  }
}