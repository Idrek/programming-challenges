(ns tex-quotes.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def double-quote \")
(def single-quotes ["``" "´´"])
(def replacement (atom -1))

(defn -main
  "https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=4&page=show_problem&problem=208"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [line (line-seq reader)]
        (let [rep @replacement
              line (map
                     #(if (= % double-quote)
                        (do
                          (swap! replacement (fn [state] (inc state)))
                          (nth single-quotes (mod @replacement 2)))
                        %)
                     line)]
          (println (str/join "" line)))))))
