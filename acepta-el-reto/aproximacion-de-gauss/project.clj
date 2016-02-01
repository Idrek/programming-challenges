(defproject aproximacion-de-gauss "0.1.0-SNAPSHOT"
  :description "Acepta el reto :: Aproximacion de Gauss :: https://www.aceptaelreto.com/problem/statement.php?id=107"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.hypirion/primes "0.2.1"]]
  :main ^:skip-aot aproximacion-de-gauss.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
