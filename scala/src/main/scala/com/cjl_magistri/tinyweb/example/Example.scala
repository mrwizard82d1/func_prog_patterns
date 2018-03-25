package com.cjl_magistri.tinyweb.example
import com.cjl_magistri.tinyweb.FunctionView

object Example {
  def greetingViewRenderer(model: Map[String, List[String]]): String =
    "<h1>Friendly Greetings:%s</h1".format(
      model
        getOrElse("greetings", List[String]())
        map renderGreeting
        mkString ", "
    )

  private def renderGreeting(greeting: String) =
    "<h2>%s</h2>".format(greeting)

  def greetingView = new FunctionView(greetingViewRenderer)
}
