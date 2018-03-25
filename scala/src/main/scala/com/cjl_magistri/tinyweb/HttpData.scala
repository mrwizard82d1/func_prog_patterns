package com.cjl_magistri.tinyweb

case class HttpRequest(headers: Map[String, String], body: String, path: String)

case class HttpResponse(body: String, responseCode: Integer)
