(defproject wilson-primes "0.1.0-SNAPSHOT"
  :description "Code rank :: Wilson primes"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot wilson-primes.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
