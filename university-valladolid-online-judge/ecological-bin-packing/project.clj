(defproject ecological-bin-packing "0.1.0-SNAPSHOT"
  :description "Ecological bin packing :: UVA Online Judge :: https://uva.onlinejudge.org/external/1/102.pdf"
  :url "https://github.com/Idrek/programming-challenges"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/math.combinatorics "0.1.1"]]
  :main ^:skip-aot ecological-bin-packing.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
