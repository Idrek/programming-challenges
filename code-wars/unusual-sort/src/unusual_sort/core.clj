(ns unusual-sort.core
  (:require [clojure.string :as s]
            [clojure.java.io :as io])
  (:use [clojure.tools.trace])
  (:gen-class))

(defn char-of
  [character]
  (.charAt (str character) 0))

(defn unusual-sort
  "Sort digits after letters, including their format either as number or string"
  [& chars]
  (let [chars (flatten chars)
        sub-ascii (zipmap (map char (flatten (list (range 65 91) (range 97 123) (range 48 58)))) (range))]
    (sort
      #(compare
         (get sub-ascii (char-of %1))
         (get sub-ascii (char-of %2)))
      chars)))

(defn -main
  "http://www.codewars.com/kata/un-usual-sort"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [entry (line-seq reader)]
        (println "Entry: " entry)
        (println (unusual-sort (s/split entry #"\s+")))))))
