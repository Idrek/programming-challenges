(ns next-bigger-number-with-same-digits.core
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn different-numbers-with-same-digits
  "Return a lazy-list with all permtutations of the digits of a number"
  [number]
  (let [str-number (str number)]
    (map (comp #(Integer. %) (partial s/join "")) (c/permutations str-number))))

(defn next-bigger-number-with-same-digits
  "Calculate next bigger number with its same digits in any order"
  [number]
  (let [numbers-with-same-digits (sort (different-numbers-with-same-digits number))
        number-over-target (first (drop-while #(<= % number) (sort numbers-with-same-digits)))]
      (if (nil? number-over-target)
        -1
        number-over-target)))

(defn -main
  "http://www.codewars.com/kata/next-bigger-number-with-the-same-digits"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [number (line-seq reader)]
      (println (next-bigger-number-with-same-digits (Integer. number)))))))