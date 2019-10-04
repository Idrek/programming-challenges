(ns find-pair.core
  (:require [clojure.string :as clj-str])
  (:gen-class))

(defn nths [coll indexes]
  (for [i indexes] (nth coll i)))

(defmulti find-pairs (fn [_ _ mode] mode))

(defmethod find-pairs :naive [coll sum _]
  (let [coll-size (count coll)]
    (into [] (filter
              (complement nil?)
              (for [i (range 0 (dec coll-size))]
                (reduce
                 (fn [acc j]
                   (when (= (apply + (nths coll [i j])) sum)
                     (reduced [i j])))
                 nil
                 (range (inc i) coll-size)))))))

(defmethod find-pairs :sorting [coll sum _]
  (let [mapped-coll (into {} (map-indexed #(vector %2 %1) coll))
        find-coll-positions (fn [mapped-coll coll low high]
                             (let [low-value (nth coll low)
                                   high-value (nth coll high)]
                               [(get mapped-coll low-value)
                                (get mapped-coll high-value)]))
        sorted-coll (sort coll)]
    (loop [low 0
           high (dec (count coll))
           pairs []]
      (if (<= high low)
        pairs
        (let [low+high (apply + (nths sorted-coll [low high]))]
          (cond
            (= sum low+high)
            (recur (inc low)
                   (dec high)
                   (conj pairs (vec (sort (find-coll-positions
                                           mapped-coll sorted-coll low high)))))
            (< sum low+high) (recur low (dec high) pairs)
            :else (recur (inc low) high pairs)))))))

(defmethod find-pairs :hashing [coll sum _]
  (second
   (reduce
    (fn [[aux-map pairs] [i elem]]
      (if-let [map-elem (get aux-map (- sum elem))]
        [aux-map (conj pairs [map-elem i])]
        [(assoc aux-map elem i) pairs]))
    [{} []]
    (map-indexed #(vector %1 %2) coll))))

(defn print-pairs [coll mode]
  (if (empty? coll)
    (println "Pair not found")
    (doseq [[i j] coll]
      (println
       (format "(%s) Pair found at index %d and %d"
               (clj-str/capitalize (name mode)) i j)))))

(defn -main [& args]
  (let [coll [8 7 2 5 3 1]
        sum 10]
    (as-> :naive $
      (print-pairs (find-pairs coll sum $) $))
    (as-> :sorting $
      (print-pairs (find-pairs coll sum $) $))
    (as-> :hashing $
      (print-pairs (find-pairs coll sum $) $))))
