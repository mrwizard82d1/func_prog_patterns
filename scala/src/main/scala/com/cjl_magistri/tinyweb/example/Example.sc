def greetingViewRenderer(model: Map[String, List[String]]) =
  "<h1>Friendly Greetings:%s</h1".format(
    model
      getOrElse("greetings", List[String]())
      map(renderGreeting)
      mkString ", "
  )

private def renderGreeting(greeting: String) =
  "<h2>%s</h2>".format(greeting)

def greetingView = new FunctionView(greetingViewRenderer)
