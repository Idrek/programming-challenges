(defproject find-pair "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha13"]]
  :main ^:skip-aot find-pair.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
