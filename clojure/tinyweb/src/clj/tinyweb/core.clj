(ns tinyweb.core
  (:gen-class)
  (:import (com.cjl_magistri.tinyweb RenderingException)))

(defn proto-controller [http-request]
      {:name (http-request :body)})

(defn proto-view [model]
  (str "<h1>Hello, " (model :name) "</h1>"))

(defn render [view model]
  (try
    (view model)
    (catch Exception e
      (throw (RenderingException. e)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (str "(proto-controller {:body \"Mike\"}): " (proto-controller {:body "Mike"})))
  (println (str "(proto-view (proto-controller {:body \"Mike\"})): "
                (proto-view (proto-controller {:body "Mike"}))))
  (println (str "(render proto-view (proto-controller {:body \"Mike\"})): "
                (render proto-view (proto-controller {:body "Mike"})))))
