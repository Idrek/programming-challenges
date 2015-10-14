(defproject holes-in-text "0.1.0-SNAPSHOT"
  :description "Code chef :: Holes in the text"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot holes-in-text.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
