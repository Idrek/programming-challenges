(ns ordering-the-soldiers.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (loop [inputs (Integer. (first (line-seq reader)))]
        (when (> inputs 0)
          (let [count (Integer. (first (line-seq reader)))
                ;; Join indexes to soldiers movements, substract them and sort.
                ;; Example:
                ;;   Input -> "0 1 2 0 1"
                ;;   Split and convert to integers
                ;;   Add indexes beginning by one: ([1 0] [2 1] [3 2] [4 0] [5 1])
                ;;   Include the result of substraction: ([1 1 0] [1 2 1] [1 3 2] [4 4 0] [4 5 1])
                ;;   Sort by first result, but in case of draw, last one wins, so second must be
                ;;     descendant: ([1 3 2] [1 2 1] [1 1 0] [4 5 1] [4 4 0])
                ;;   Get initial positions of soldiers: (3 2 1 5 4)
                ;;   Format for output
                soldiers
                  (as-> (first (line-seq reader)) $
                    (str/split $ #"\s+" count)
                    (map #(Integer. %) $)
                    (map-indexed (fn [idx n] [(inc idx) n]) $)
                    (map (fn [[i num]] [(- i num) i num]) $)
                    (sort-by (juxt first (comp - second)) $)
                    (map second $)
                    (str/join " " $))]
            (println soldiers))
          (recur (dec inputs)))))))
