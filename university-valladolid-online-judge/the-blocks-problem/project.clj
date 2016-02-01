(defproject the-blocks-problem "0.1.0-SNAPSHOT"
  :description "UVA Online Judge :: The blocks problem :: https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=37"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot the-blocks-problem.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
