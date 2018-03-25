(defproject tinyweb "0.1.0-SNAPSHOT"
  :description "The tinyweb application from 'Functional Programming Patterns in Scala and Clojure.'"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :main ^:skip-aot tinyweb.core
  :target-path "target/%s"
  :source-paths ["src/clj"]
  :java-source-paths ["src/java"]
  :profiles {:uberjar {:aot :all}})
