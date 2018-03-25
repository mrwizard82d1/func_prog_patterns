(ns tinyweb.core
  (:require [clojure.string :as str])
  (:gen-class)
  (:import (com.cjl_magistri.tinyweb HttpRequest HttpRequest$Builder)))

(defn proto-controller [http-request]
      {:name (.getBody http-request)})

(def proto-builder (HttpRequest$Builder/newBuilder))

(def proto-http-request (.. proto-builder
                            (body "Mike")
                            (path "/say-hello")
                            build))

(defn proto-controller-with-map [http-request]
      {:name (http-request :body)})

(defn print-http-request [http-request]
  (let [prefix-extractor-map {:headers #(.getHeaders %)
                              :path #(.getPath %)
                              :body #(.getBody %)}]
    (str/join ", " (map #(str (name %1) " = " ((%1 prefix-extractor-map) http-request))
                        [:headers :path :body]))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (str "proto-http-request: " (print-http-request proto-http-request)))
  (println (str "(proto-controller proto-http-request): " (proto-controller proto-http-request)))
  (println (str "(proto-controller-with-map {:body \"Mike\"}: " (proto-controller-with-map {:body "Mike"}))))
