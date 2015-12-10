(ns count-most-frequent-item-in-array.core
  (:gen-class))

(defn count-most-frequent-item
  "http://www.codewars.com/kata/find-count-of-most-frequent-item-in-an-array"
  [l]
  (cond
    (empty? l) 0
    :else (apply max (vals (frequencies l)))))
