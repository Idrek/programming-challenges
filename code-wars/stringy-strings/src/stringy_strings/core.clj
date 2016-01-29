(ns stringy-strings.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn stringy-strings
  "http://www.codewars.com/kata/stringy-strings"
  [size]
  (str/join "" (take size (flatten (repeat [1 0])))))
