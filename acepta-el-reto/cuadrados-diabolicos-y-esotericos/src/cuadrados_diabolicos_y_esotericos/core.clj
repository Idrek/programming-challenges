(ns cuadrados-diabolicos-y-esotericos.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s])
  (:use [clojure.tools.trace])
  (:gen-class))

(defn sum-rows-cells
  "Assuming 'matrix' is implemented as a sequence of sequences, it returns a
  sequence with the sums of each row"
  [matrix]
  (map #(apply + %) matrix))

(defn sum-columns-cells
  "Assuming 'matrix is implemented as a sequence of sequences, it returns a
  list with one element, the sum of one element per row, indicated by the
  sequence of 'columns'"
  [matrix columns]
  (list (apply +
          (for [[k v] (zipmap matrix columns)]
            (get (vec k) v)))))

(defn check-natural-number-cells
  "Check that cell numbers follow the natural number serie"
  [matrix]
  (let [num-elements (.intValue (Math/pow (count matrix) 2))]
    (= (sort (flatten matrix)) (range 1 (inc num-elements)))))

(defn is-diabolical
  "Check if matrix is diabolical: Sums by row, column and main diagonals are the same.
  If so, it returns the magic constant number, otherwise 'nil'"
  [matrix]
  (let [num-rows (Integer. (count matrix))
        matrix-transposed (apply mapv vector matrix)
        diagonal (range num-rows)
        diagonal-reversed (range (dec num-rows) -1 -1)
        sums (concat
               (sum-rows-cells matrix)
               (sum-rows-cells matrix-transposed)
               (sum-columns-cells matrix diagonal)
               (sum-columns-cells matrix diagonal-reversed))]
    (if (apply = sums)
      (first sums)
      nil)))

(defn esoteric-sides-cells-odd-matrix
  "Calculate CM2 with the sum of center cells of each side in a matrix with
  odd number of rows and columns"
  [matrix]
  (let [num-rows (Integer. (count matrix))
        middle-position (int (/ num-rows 2))
        first-row (first matrix)
        middle-row (nth matrix middle-position)
        last-row (last matrix)]
    (+ (nth first-row middle-position) (nth last-row middle-position) (first middle-row) (last middle-row))))

(defn esoteric-central-cell-odd-matrix
  "Calculate CM2 with the central cell multiplied by four in a matrix with
  odd number of rows and columns"
  [matrix]
  (let [num-rows (Integer. (count matrix))
        middle-cell-position (int (/ num-rows 2))
        row (nth matrix middle-cell-position)
        cell (nth row middle-cell-position)]
    (* cell 4)))

(defn esoteric-edge-cells
  "Calculate CM2 with the sum of edge cells in any kind of matrix (even or
  odd number of rows and columns)"
  [matrix]
  (let [first-row (first matrix)
        last-row (last matrix)]
    (+ (first first-row) (last first-row) (first last-row) (last last-row))))

(defn esoteric-central-cell-even-matrix
  "Calculate CM2 with the sum of central cells in a matrix with even number
  of rows and columns"
  [matrix]
  (let [num-rows (Integer. (count matrix))
        middle-position (/ num-rows 2)
        middle-low-row (vec (nth matrix (dec middle-position)))
        middle-high-row (vec (nth matrix middle-position))]
    (apply + (concat
               (subvec middle-low-row (dec middle-position) (inc middle-position))
               (subvec middle-high-row (dec middle-position) (inc middle-position))))))

(defn esoteric-sides-cells-even-matrix
  "Calculate CM2 with the half of the sum of center cells of each side in a
  matrix with even number of rows and columns"
  [matrix]
  (let [num-rows (Integer. (count matrix))
        middle-position (/ num-rows 2)
        first-row (vec (first matrix))
        middle-low-row (vec (nth matrix (dec middle-position)))
        middle-high-row (vec (nth matrix middle-position))
        last-row (vec (last matrix))]
    (/ (apply + (concat
                  (subvec first-row (dec middle-position) (inc middle-position))
                  (subvec last-row (dec middle-position) (inc middle-position))
                  [(first middle-low-row) (last middle-low-row) (first middle-high-row) (last middle-high-row)])) 2)))

(defn is-esoteric
  "Check if matrix is esoteric:
    - Its cell numbers follow the natural number serie
    - It has equal:
      - Sum of center cells of each side (special case for matrix with even number of rows)
      - Sum of edge cells
      - Sum of four times the center cell (special case for matrix with even number of rows)
    - In that case return that number, which is called 'Magic constant 2'
    - Otherwise return 'nil'"
  [matrix]
  (let [num-rows (Integer. (count matrix))]
    (if (and (true? (check-natural-number-cells matrix)) (odd? num-rows))
      (let [results (list
                      (esoteric-central-cell-odd-matrix matrix)
                      (esoteric-edge-cells matrix)
                      (esoteric-sides-cells-odd-matrix matrix))]
        (if (apply = results)
          (first results)
          nil))
      (let [results (list
                      (esoteric-central-cell-even-matrix matrix)
                      (esoteric-edge-cells matrix)
                      (esoteric-sides-cells-even-matrix matrix))]
        (if (apply = results)
          (first results)
          nil)))))

(defn -main
  "https://www.aceptaelreto.com/problem/statement.php?id=101"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [[str-matrix-num-rows str-matrix] (partition 2 (line-seq reader)) :while (not= str-matrix-num-rows \0)]
        (let [matrix-num-rows (Integer/parseInt str-matrix-num-rows)
              matrix (doall (partition matrix-num-rows (map #(Integer/parseInt %) (s/split str-matrix #"\s+"))))]
          (if-let [magic-constant (is-diabolical matrix)]
            (if-let [magic-constants-relationship (= (is-esoteric matrix) (/ (* 4 magic-constant) matrix-num-rows))]
              (printf "%s\n" "ESOTERICO")
              (printf "%s\n" "DIABOLICO"))
            (printf "%s\n" "NO")))))))

