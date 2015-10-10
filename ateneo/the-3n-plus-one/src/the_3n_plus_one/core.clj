(ns the-3n-plus-one.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s])
  (:gen-class))

(defn cycle-length
  "Calculate the cycle-length of a number:
    - If number is odd, cycle with result of (3n+1)
    - If number is even, cycle with result of (n/2)
    - Until number gets to 1
    - Then returns the number of steps done"
  [number]
  (loop [n number
         length 0]
    (if (= n 1)
      (inc length)
      (recur (if (even? n) (/ n 2) (+ (* 3 n) 1)) (inc length)))))

(defn -main
  "http://curry.ateneo.net/acm-icpc/v1/100.html"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [line (line-seq reader)]
        (let [[number-low number-high] (map #(Integer/parseInt %) (s/split line #"\s+"))]
          (printf "%d %d %d\n" number-low number-high (apply max (map cycle-length (range number-low number-high)))))))))
