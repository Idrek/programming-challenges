#!/usr/bin/env boot

(set-env!
  :resource-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.8.0" :scope "provided"]])

(defn -main
  "Code Chef :: Ordering the soldiers :: https://www.codechef.com/problems/ORDERS"
  [& args]
  (require 'ordering-the-soldiers.core)
  (apply (resolve 'ordering-the-soldiers.core/-main) args))