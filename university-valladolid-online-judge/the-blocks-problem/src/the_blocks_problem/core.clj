(ns the-blocks-problem.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(def QUIT "quit")

(def block-world (atom {}))
(defn get-block-world [] @block-world)
(defn set-block-world [key val] (swap! block-world assoc-in key val))
(defn get-location-block [block] (get-in (get-block-world) [block 0]))
(defn get-stack-block [location] (get-in (get-block-world) [location 1]))
(defn get-blocks-below [stack block] (conj (into [] (take-while #(not= block %) stack)) block))
(defn get-blocks-above [stack block] (drop-while #(not= block %) stack))
(defn print-block-world []
  (let [block-world (get-block-world)]
    (dorun (map #(printf "%d: %s\n" % (str/join " " (get-stack-block %))) (sort (keys block-world))))))

(defn mark-block-new-location
  "Mark the location where this block is going to move"
  [block-from location-block-to]
  (set-block-world [block-from 0] location-block-to))

(defn move
  "Move 'from-block' to the stack of 'block-to'"
  [block-from block-to]
  (let [location-block-from (get-location-block block-from)
        location-block-to (get-location-block block-to)]
    (when (not= location-block-from location-block-to)
      (let [stack-block-from (get-stack-block location-block-from)
            stack-block-to (get-stack-block location-block-to)]
        (set-block-world [location-block-from 1] (into [] (butlast stack-block-from)))
        (set-block-world [location-block-to 1] (conj stack-block-to block-from))))))

(defn return-blocks-to-original-locations
  "Return blocks above 'block' to their initial locations"
  [block]
  (let [location-block (get-location-block block)
        stack (get-stack-block location-block)
        blocks-stacked-below (get-blocks-below stack block)
        blocks-stacked-above (get-blocks-above stack block)]
    (set-block-world [location-block 1] blocks-stacked-below)
    (doall (map #(do
                   (set-block-world [% 0] %)
                   (set-block-world [% 1] [%]))
             blocks-stacked-above))))

(defn pile
  "Move 'block-from' and all above it to the stack of 'block-to'"
  [block-from block-to]
  (let [location-block-from (get-location-block block-from)
        location-block-to (get-location-block block-to)]
    (when (not= location-block-from location-block-to)
      (let [stack-from (get-stack-block location-block-from)
            stack-to (get-stack-block location-block-to)
            stack-above-block-from (get-blocks-above stack-from block-from)
            stack-below-block-from (get-blocks-below stack-from block-from)]
        (set-block-world [location-block-to 1] (into [] (concat stack-to stack-above-block-from)))
        (set-block-world [location-block-from 1] (into [] (butlast stack-below-block-from)))))))

(defmulti block-action
  "Decide which action to take based in the instruction given by the user"
  (fn [action block-from position block-to]
    (str action "::" position)))

(defmethod block-action "move::onto"
  [action block-from position block-to]
  (let [location-block-to (get-location-block block-to)]
    (return-blocks-to-original-locations block-from)
    (return-blocks-to-original-locations block-to)
    (move block-from block-to)
    (mark-block-new-location block-from location-block-to)))

(defmethod block-action "move::over"
  [action block-from position block-to]
  (let [location-block-to (get-location-block block-to)]
    (return-blocks-to-original-locations block-from)
    (move block-from block-to)
    (mark-block-new-location block-from location-block-to)))

(defmethod block-action "pile::onto"
  [action block-from position block-to]
  (return-blocks-to-original-locations block-to)
  (pile block-from block-to))

(defmethod block-action "pile::over"
  [action block-from position block-to]
  (pile block-from block-to))

(defn -main
  "http://curry.ateneo.net/acm-icpc/v1/101.html"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (let [number-blocks (Integer. (first (line-seq reader)))]
        (doall (map #(set-block-world [%] [% [%]]) (range number-blocks)))
        (loop [instruction (first (line-seq reader))]
          (if (not= QUIT instruction)
            (let [[action block-from position block-to] (str/split instruction #"\s+")
                  [block-from block-to] (map #(Integer/parseInt %) (list block-from block-to))]
              (block-action action block-from position block-to)
              (recur (first (line-seq reader))))
            (print-block-world)))))))
