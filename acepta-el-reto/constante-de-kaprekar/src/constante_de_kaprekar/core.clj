(ns constante-de-kaprekar.core
  (:require [clojure.java.io :as io]
            [clojure.string :as s])
  (:gen-class))

(defn- format-number
  [number]
  (format "%04d" number))

(defn- is-repdigit
  "Check if all digits of a number are the same"
  [number]
  (= (count (into #{} (format-number number))) 1))

(defn- is-kaprekar-constant
  [number]
  (= number 6174))

(defn- numbers-with-digits-ordered
  "From one number as input it returns a list with two numbers, the first
  one with its digits ordered in descendant and the second one with its
  digits ordered in ascendant"
  [number]
  (let [list-digits-asc (sort (format-number number))
        list-digits-desc (reverse list-digits-asc)
        nums-with-digits-ordered
          (map (comp #(Integer/parseInt %) (partial s/join "")) (list list-digits-desc list-digits-asc))]
    nums-with-digits-ordered))

(defn- kaprekar-iterations
  "Return the number of iterations needed to apply to input number to get the Kraprekar constant"
  [number]
  (loop [[number-digits-desc number-digits-asc] (numbers-with-digits-ordered number)
         iterations 0]
    (let [result (- number-digits-desc number-digits-asc)]
      (cond
        (true? (is-kaprekar-constant result)) (if (= 0 iterations) iterations (inc iterations))
        (true? (is-repdigit result)) 8
        :else (recur (numbers-with-digits-ordered result) (inc iterations))))))

(defn -main
  "https://www.aceptaelreto.com/problem/statement.php?id=100"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (let [num-inputs (Integer/parseInt (first (line-seq reader)))
            lazy-numbers (map #(Integer/parseInt %) (line-seq reader))
            numbers (take num-inputs lazy-numbers)]
        (doseq [num-iterations (map kaprekar-iterations numbers)]
          (println num-iterations))))))
