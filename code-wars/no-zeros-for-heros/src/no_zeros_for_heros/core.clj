(ns no-zeros-for-heros.core
  (:require [cuerdas.core :as c])
  (:gen-class))

(defn no-boring-zeros
  [n]
  (Integer/parseInt (c/rtrim (str n) "0")))

(defn -main
  [& args])
