(ns wilson-primes.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn factorial
  "Returns the factorial of the previous number passed as parameter"
  [number]
  (loop [n number
         acc 1]
    (if (<= n 1) acc (recur (dec n) (*' n acc)))))

(defn is-wilson-prime
  "Checks it the number is a Wilson prime, so if the formula ((P-1)! + 1) / (P * P)
  returns an integer value"
  [number]
  (let [factorial (factorial (dec number))]
    (= 0 (rem (inc factorial) (int (Math/pow number 2))))))

(defn -main
  "http://www.codewars.com/kata/wilson-primes/"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [str-number (line-seq reader)]
        (when (is-wilson-prime (Integer. str-number))
          (println str-number true))))))
