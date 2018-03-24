package com.cjl_magistri.tinyweb

/**
  * View is an interface with a single method, render, used to render the model into HTML.
  */
trait View {
  /**
    * Renders the model as an HTML fragment.
    *
    * @param model The map between model keys and one or more values.
    * @return A string containing an HTML fragment rendering the model.
    */
  def render(model: Map[String, List[String]]): String
}

/**
  * An implementation of the View interface.
  *
  * This class plays the role of Strategy in the strategy design pattern, defining an Algorithmic Interface,
  * veiweRenderer, that is implemented by different ConcreteStrategies.
  *
  * Because we utilizing functional programming to implement this pattern, we need *not* implement an actual
  * ConcreteStrategy. Instead, we pass viewRenderer, the AlgorithmicInterface method from the ConcreteStrategy into
  * the Strategy instance itself.
  *
  * @param viewRenderer The concrete, AlgorithmicInterface, used to render the view.
  */
class FunctionView(viewRenderer: (Map[String, List[String]]) => String) extends View {
  def render(model: Map[String, List[String]]) =
    try {
      viewRenderer(model)
    } catch {
      case ex: Exception => throw new RenderingException(ex)
    }
}