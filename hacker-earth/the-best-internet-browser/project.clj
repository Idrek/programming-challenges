(defproject the-best-internet-browser "0.1.0-SNAPSHOT"
  :description "Hacker earth :: The best internet browser"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot the-best-internet-browser.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
