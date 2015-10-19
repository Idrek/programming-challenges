(ns problemas-de-herencia.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(def land-limits {:inf 0 :sup 1})
(def diff 0.001)
(def winner {:cain "CAIN" :abel "ABEL" :justo "JUSTO"})
(def end-input "20")

(defn create-polynomial
  "Create a vector of vectors, with two elements each one. The first one the value
  and the second one the degree, so, for example:
  7x^3 + 4x^2 - 2x + 6
  would be represented as:
  [[7 3] [4 2] [2 1] [6 0]]"
  [degree coefs]
  (map vector coefs (range degree -1 -1)))

(defn resolve-polynomial
  "Resolve polynomial"
  [polynomial i]
  (apply + (map #(* (first %) (Math/pow i (second %))) polynomial)))

(defn rectangle-area
  "Calculate rectangle area"
  [polynomial num-rectangles rectangle]
  (/ (resolve-polynomial polynomial (/ rectangle num-rectangles)) num-rectangles))

(defn inside-low-limit
  "Check if result is outside the range in the low limit"
  [value]
  (>= value (:inf land-limits)))

(defn restrict-high-limit
  "Check if result is above the limit, and if so, set it at its maximum value"
  [value num-rectangles]
  (let [limit-sup (/ (:sup land-limits) num-rectangles)]
    (if (> value limit-sup) limit-sup value)))

(defn winner-distribution
  "Get who user gets more land"
  [total-area]
  (cond
    (< (Math/abs (- 0.5 total-area)) diff) (:justo winner)
    (< total-area 0.5) (:abel winner)
    :else (:cain winner)))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [[degree coefs rectangles] (partition 3 (line-seq reader)) :while (not= end-input degree)]
        (let [rectangles (Integer. rectangles)
              polynomial (create-polynomial (Integer. degree) (map #(Integer. %) (str/split coefs #"\s+")))
              rectangles-area (map #(rectangle-area polynomial rectangles %) (range rectangles))
              total-area (apply + (map #(restrict-high-limit % rectangles) (filter inside-low-limit rectangles-area)))]
          (println (winner-distribution total-area)))))))
