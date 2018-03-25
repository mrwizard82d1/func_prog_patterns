package com.cjl_magistri.tinyweb

class TinyWeb(routeMap: Map[String, Controller], filters: List[(HttpRequest) => HttpRequest]) {
  def handleRequest(httpRequest: HttpRequest): Option[HttpResponse] = {
    // Compose all the filters together into a single, composed filter.
    val composedFilter = filters.reverse.reduceLeft((composed, next) => composed compose next)

    // Apply all the filters to the request.
    val filteredRequest = composedFilter(httpRequest)

    // Calculate the controller (option) that handles the request and ...
    val controller = routeMap.get(filteredRequest.path)

    // Map that controller option to a request by invoking handleRequest of the controller.
    controller map { controller => controller.handleRequest(filteredRequest) }
  }
}