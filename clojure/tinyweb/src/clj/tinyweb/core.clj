(ns tinyweb.core
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

(defn- test-controller [http-request]
  {:name (http-request :body)})

(defn- test-view [model]
  (str "<h1>Hello, ", (model :name) "</h1>"))

(def test-request-handler {:controller test-controller
                           :view test-view})

(def test-http-request {:body "Mike"
                        :path "/say-hello"
                        :headers {}})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (str "(execute-request test-http-request test-request-handler): "
                (execute-request test-http-request test-request-handler))))

