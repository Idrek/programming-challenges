(ns diagonal-difference.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s])
  (:use [clojure.tools.trace])
  (:gen-class))

(defn get-columns-sum
  "Matrix is a sequence of sequences
  Columns is a sequence.
  It return the sum of one column per row indicated by the 'columns' var"
  [matrix columns]
  (let [matrix-with-columns (map vector matrix columns)]
    (reduce (fn [acum matrix-row] (+ acum (get (vec (first matrix-row)) (second matrix-row)))) 0 matrix-with-columns)))

(defn get-diagonal-difference
  "Difference between the sum of values in the diagonal from the right and the diagonal from the left"
  ([matrix]
    (get-diagonal-difference matrix (count matrix)))
  ([matrix num-rows]
    (let [diagonal-right (range num-rows)
          diagonal-left (reverse diagonal-right)]
      (- (get-columns-sum matrix diagonal-right) (get-columns-sum matrix diagonal-left)))))

(defn -main
  "https://www.hackerrank.com/challenges/diagonal-difference"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (let [num-rows (Integer. (first (line-seq reader)))
            matrix (partition num-rows (map #(Integer. %) (s/split (slurp reader) #"\s+")))]
        (println (Math/abs (get-diagonal-difference matrix num-rows)))))))
