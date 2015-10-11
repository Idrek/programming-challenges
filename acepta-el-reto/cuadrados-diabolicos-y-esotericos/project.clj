(defproject cuadrados-diabolicos-y-esotericos "0.1.0-SNAPSHOT"
  :description "Acepta el reto :: Cuadrados diabolicos y esotericos"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.trace "0.7.5"]]
  :main ^:skip-aot cuadrados-diabolicos-y-esotericos.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
