(ns moviles.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.zip :as z])
  (:gen-class))

(def SUBMOVIL-ELEMENTS 4)

;; :left => Left branch from the root
;; :right => Right branch from the root
;; :d => Distance from root to first submovil (a scalar because it's the only one needed to do calculations)
;; :p => List of weights of all submoviles
;; :zeroes => Counter to point out how many submoviles has this branch)
(def MOVIL-INITIAL-STATE {:left { :d 0 :p [] :zeroes 0 } :right { :d 0 :p [] :zeroes 0}})

(def BRANCH-INITIAL-STATE :left)

(def BRANCHES [:left :right])

(defmulti edit-movil
  (fn [movil lines-read movil-data branch zl zr]
    (if (= 0 lines-read) "init" "update")))

(defmethod edit-movil "init"
  [movil lines-read movil-data branch zl zr]
  (-> movil
    (assoc-in [:left :zeroes] zl)
    (assoc-in [:right :zeroes] zr)
    (assoc-in [:left :d] (get-in movil-data [:left 1]))
    (assoc-in [:right :d] (get-in movil-data [:right 1]))
    (update-in [:left :p] conj (get-in movil-data [:left 0]))
    (update-in [:right :p] conj (get-in movil-data [:right 0]))))

(defmethod edit-movil "update"
  [movil lines-read movil-data branch zl zr]
  (-> movil
    (update-in [branch :p] conj (get-in movil-data [:left 0]) (get-in movil-data [:right 0]))
    (update-in [branch :zeroes] + zl zr)))

(defn branch-balance
  [movil]
  (map #(* (get-in movil [% :d]) (apply + (get-in movil [% :p]))) BRANCHES))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (loop [lines-read 0
             ;; Counter of zeroes found to know how many submoviles are left to read)
             zeroes 0
             line (first (line-seq reader))
             movil MOVIL-INITIAL-STATE
             ;; It points out to which branch add weights of submoviles)
             branch BRANCH-INITIAL-STATE]
        (when-not (nil? line)
          (let [digits-line (map #(Integer/parseInt %) (str/split line #"\s+" SUBMOVIL-ELEMENTS))
                movil-data (apply hash-map (interleave [:left :right] (map #(into [] %) (split-at 2 digits-line))))
                [zl zr] (->> (split-at 2 digits-line) (map #(filter zero? %)) (map count))
                zeroes (+ zeroes zl zr)
                branch (if (> lines-read (get-in movil [:left :zeroes])) :right :left)]
            (if (= lines-read zeroes)
              (do
                (let [movil (edit-movil movil lines-read movil-data branch zl zr)
                      [left-branch right-branch] (branch-balance movil)]
                  (println (if (= left-branch right-branch) "SI" "NO")))
                (recur 0 0 (first (line-seq reader)) MOVIL-INITIAL-STATE BRANCH-INITIAL-STATE))
              (recur
                (inc lines-read)
                zeroes
                (first (line-seq reader))
                (edit-movil movil lines-read movil-data branch zl zr)
                branch))))))))