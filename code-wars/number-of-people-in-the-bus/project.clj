(defproject number-of-people-in-the-bus "0.1.0-SNAPSHOT"
  :description "Codewars :: Number of people in the bus"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot number-of-people-in-the-bus.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
