(defproject stringy-strings "0.1.0-SNAPSHOT"
  :description "Code wars :: Stringy strings"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :main ^:skip-aot stringy-strings.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
