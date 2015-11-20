(ns number-of-people-in-the-bus.core
  (:gen-class))

(defn people-in-bus
  [bus-stops]
  (reduce #(+ %1 (- (first %2) (second %2))) 0 bus-stops))
