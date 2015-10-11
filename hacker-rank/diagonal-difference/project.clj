(defproject diagonal-difference "0.1.0-SNAPSHOT"
  :description "Hacker rank :: Diagonal difference"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.trace "0.7.5"]]
  :main ^:skip-aot diagonal-difference.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
