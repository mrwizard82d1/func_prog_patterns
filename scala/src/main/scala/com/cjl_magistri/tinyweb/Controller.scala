package com.cjl_magistri.tinyweb

/**
  * This class specifies the services offered to all Controllers.
  */
trait Controller {
  /**
    * Create the HttpResponse appropriate to the issued HttpRequest.
    *
    * @param httpRequest The request issued by a client.
    * @return The HttpResponse instance appropriate to the HttpRequest instance.
    */
  def handleRequest(httpRequest: HttpRequest): HttpResponse
}

/**
  * The concrete, functional controller.
  *
  * This class plays the role of AbstractClass in the Template Method design pattern. In this role, it implements the
  * Template Method, handleRequest, in terms of the Primitive Operation, doRequest.
  *
  * @param view The View to be rendered.
  * @param doRequest The concrete implementation of Primitive Operation. In a functional scenario, instead of
  *                  creating an instance of a ConcreteClass inheriting from AbstractClass, the client supplies the
  *                  concrete implementation of PrimitiveOperation.
  */
class FunctionController(view: View, doRequest: (HttpRequest) => Map[String, List[String]]) extends Controller {
  def handleRequest(request: HttpRequest): HttpResponse =
    try {
      val model = doRequest(request)
      val responseBody = view.render(model)
      HttpResponse(responseBody, 200)
    } catch {
      case e: ControllerException =>
        HttpResponse("", e.getStatusCode)
      case e: RenderingException =>
        HttpResponse("Exception while rendering", 500)
      case e: Exception =>
        HttpResponse("", 500)
    }
}