(ns next-bigger-number-with-same-digits.core
  (:require [clojure.string :as s]
            [clojure.math.combinatorics :as c])
  (:gen-class))

(defn different-numbers-with-same-digits
  "Return a lazy-list with all permtutations of the digits of a number"
  [number]
  (let [str-number (str number)]
    (map (comp #(Integer. %) (partial s/join "")) (c/permutations str-number))))

(defn next-bigger-number-with-same-digits
  "http://www.codewars.com/kata/next-bigger-number-with-the-same-digits"
  [number]
  (let [number (Integer. number)
        numbers-with-same-digits (sort (different-numbers-with-same-digits number))
        number-over-target (first (drop-while #(<= % number) (sort numbers-with-same-digits)))]
      (if (nil? number-over-target)
        -1
        number-over-target)))
