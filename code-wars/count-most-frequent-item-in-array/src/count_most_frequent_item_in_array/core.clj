(ns count-most-frequent-item-in-array.core
  (:gen-class))

(defn count-most-frequent-item
  [l]
  (cond
    (empty? l) 0
    :else (apply max (vals (frequencies l)))))
