(defproject next-bigger-number-with-same-digits "0.1.0-SNAPSHOT"
  :description "Code wars :: Next bigger number with same digits "
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/math.combinatorics "0.1.1"]]
  :main ^:skip-aot next-bigger-number-with-same-digits.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
