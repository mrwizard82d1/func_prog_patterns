(ns tinyweb.core
  (:require [clojure.string :as str])
  (:gen-class)
  (:import (com.cjl_magistri.tinyweb RenderingException ControllerException)))

(defn- render [view model]
  (try
    (view model)
    (catch Exception e
      (throw (RenderingException. e)))))

(defn- execute-request [http-request handler]
  (let [controller (handler :controller)
        view (handler :view)]
    (try
      {:status-code 200
       :body (render view
               (controller http-request))}
     (catch ControllerException e
       {:status-code (.getStatusCode e)
        :body ""})
     (catch RenderingException e
       {:status-code 500
        :body "Exception while rendering"})
     (catch Exception e
       (.printStackTrace e)
       {:status-code 500
        :body 500}))))

(defn- apply-filters [filters http-request]
  (let [composed-filters (reduce comp (reverse filters))]
    (composed-filters http-request)))

(defn tinyweb [request-handlers filters]
  (fn [http-request]
    (let [filtered-request (apply-filters filters http-request)
          path (http-request :path)
          handler (request-handlers path)]
      (execute-request filtered-request handler))))

(defn- make-greeting [name]
  (let [greetings ["Hello" "Greetings" "Salutations" "Hola"]
        greeting-count (count greetings)]
    (str (greetings (rand-int greeting-count)) ", " name)))

(defn- handle-greeting [http-request]
  {:greetings (map make-greeting (str/split (:body http-request) #","))})

(defn- render-greeting [greeting]
  (str "<h2>" greeting "</h2>"))

(defn- greeting-view [model]
  (let [rendered-greetings (str/join "\n " (map render-greeting (:greetings model)))]
    (str "<h1>Friendly Greetings</h1>\n " rendered-greetings)))

(defn- logging-filter [http-request]
  (println (str "In logging-filter(" (:path http-request) ")")))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (let [request {:path "/greeting"
                 :body "Mike,Joe,John,Steve"}]
    (println (str "(handle-greeting request): " (handle-greeting request)))
    (println (str "(greeting-view (handle-greeting request): " (greeting-view (handle-greeting request))))
    (println (str "(logging-filter request) - side effect"))
    (logging-filter request)))

