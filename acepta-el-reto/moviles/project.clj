(defproject moviles "0.1.0-SNAPSHOT"
  :description "Acepta el reto :: Moviles :: https://www.aceptaelreto.com/problem/statement.php?id=104"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot moviles.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
