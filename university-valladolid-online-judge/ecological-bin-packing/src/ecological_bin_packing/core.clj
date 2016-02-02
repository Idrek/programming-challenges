(ns ecological-bin-packing.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo])
  (:gen-class))

(defn bins-combinations
  "Generate a map with the permutations of each bin associated with the position of
  the array of bottles to discard:
  {B 0 G 1 C 2}
  {B 0 G 2 C 1}
  {B 1 G 0 C 2}
  ..."
  []
  (let [bins [\B \G \C]]
    (->> bins (combo/permutations) (map #(zipmap %1 (range 3))))))

(defn bottle-movements
  "Create a map with a string with the sequence of bins, as key. And the number of
  bottle movements between bins, as value."
  [grouped-bottles bin-combo]
  (->> bin-combo
    (reduce-kv
      (fn [grouped-bottles bin position]
        (assoc-in grouped-bottles [bin position] 0))
      grouped-bottles)
    (hash-map (->> bin-combo (sort-by val) (keys) (str/join "")))
    (reduce-kv
      (fn [acc bin-colors bottles]
       (assoc acc (apply + (apply concat (vals bottles))) bin-colors))
      {})))

(defn next-line
  "Read next input line"
  [reader]
  (first (line-seq reader)))

(defn group-bottles-by-bin
  "Group bottles of same color in same bin, so from input like:

  1 2 3 4 5 6 7 8 9
  -----------------
  B G C B G C B G C

  yield:
  {B [1 4 7], G [2 5 8], C [3 6 9]}"
  [bottles]
  (->> (str/split bottles #"\s+")
    (map #(Integer/parseInt %))
    (partition 3)
    (apply map vector)
    (zipmap [\B \G \C])))

(defn get-best-bin-configuration
  "Given a list of maps with all bin configurations, select the best one (less movements).
  Example input:
  ({70 BGC} {65 BCG} {55 GBC} {70 GCB} {50 CBG} {70 CGB})
  with output:
  [50 CBG]"
  [bottle-movements]
  (ffirst (sort (map #(into [] %) bottle-movements))))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (loop [bottles (next-line reader)]
        (when-not (nil? bottles)
          (let [grouped-bottles (group-bottles-by-bin bottles)
                bottle-movements
                  (for [bin-combo (bins-combinations)]
                    (bottle-movements grouped-bottles bin-combo))]
            (apply printf "%s %d\n" (reverse (get-best-bin-configuration bottle-movements)))
            (recur (next-line reader))))))))

