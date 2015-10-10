(defproject constante-de-kaprekar "0.1.0-SNAPSHOT"
  :description "Acepta el reto :: Constante de Kaprekar"
  :url "https://github.com/Idrek/programming-challenges"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot constante-de-kaprekar.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
