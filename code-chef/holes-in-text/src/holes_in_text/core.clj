(ns holes-in-text.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(def data-letter-holes
  "Data structure to keep holes of each letter"
  {
      #{\C \E \F \G \H \I \J \K \L \M \N \S \T \U \V \W \X \Y \Z} 0
      #{\A \D \O \P \Q \R} 1
      #{\B} 2
      })

(defn map-letter-holes
  "Transform 'data-letter-holes' data structure to a map with each letter as key for an
  easier lookup"
  [letter-holes]
  (reduce merge (flatten (for [[k v] data-letter-holes] (map #(hash-map % v) k)))))

(defn get-number-of-holes
  "Count by letter the number of holes of the whole word"
  [letter-holes word]
  (reduce + (map #(get letter-holes %) word)))

(defn -main
  "https://www.codechef.com/problems/HOLES"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (let [number-inputs (Integer. (first (line-seq reader)))
            letter-holes (map-letter-holes data-letter-holes)]
        (doseq [word (take number-inputs (line-seq reader))]
          (println (get-number-of-holes letter-holes word)))))))
