(ns route-between-two-graph-nodes.core
  (:require [clojure.set :as set]
            [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn find-route-between-two-graph-nodes
  "Given an acyclic graph (no loops), check if exists a route between two nodes"
  [graph origin-node destination-node]
  (let [origin-node (into #{} (list (Integer. origin-node)))
        destination-node (Integer. destination-node)]
    (loop [origin-nodes origin-node
           destination-node destination-node]
      (let [nodes (reduce set/union (map #(get graph %) origin-nodes))]
        (cond
          (empty? nodes) "No route"
          (get nodes destination-node) "Found a route!!"
          :else (recur nodes destination-node))))))

(defn -main
  "http://www.codewars.com/kata/find-whether-there-is-a-route-between-two-nodes-in-a-graph"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [entry (line-seq reader)]
        (let [[origin-node destination-node] (str/split entry #"\s+")]
        (println (find-route-between-two-graph-nodes
                   {5 #{11}
                    7 #{8 11}
                    3 #{8 10}
                    11 #{2 9 10}
                    8 #{9}
                    9 #{}
                    10 #{}} origin-node destination-node)))))))
