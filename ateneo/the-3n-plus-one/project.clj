(defproject the-3n-plus-one "0.1.0-SNAPSHOT"
  :description "Ateneo :: The 3n + 1 problem"
  :url "https://github.com/Idrek/programming-challenges"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot the-3n-plus-one.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
