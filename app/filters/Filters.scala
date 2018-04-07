package filters;

import javax.inject.Inject

import global.filters.HTTPSRedirectFilter
import play.api.http.DefaultHttpFilters
import play.filters.cors.CORSFilter

class Filters @Inject() (
  cors: CORSFilter,
  httpsRedirect: HTTPSRedirectFilter
) extends DefaultHttpFilters(httpsRedirect, cors)