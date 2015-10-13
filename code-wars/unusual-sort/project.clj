(defproject unusual-sort "0.1.0-SNAPSHOT"
  :description "Code wars :: Unusual sort"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.trace "0.7.5"]]
  :main ^:skip-aot unusual-sort.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
