(ns de-nuevo-en-el-bar-de-javier.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def categories
  {\D "DESAYUNOS", \A "COMIDAS", \M "MERIENDAS", \I "CENAS", \C "COPAS"})

(def profit {:max last, :min first})

(defn get-fields
  [line]
  (str/split line #"\s+"))

(defn str2char
  [str]
  (.charAt str 0))

(defn end-of-day
  [code profits]
  (and (= \N code) (= "0" profits)))

(defn next-line
  [reader]
  (first (line-seq reader)))

(defn str2float
  [str]
  (Float/parseFloat str))

(defn get-code-of-categories
  [code]
  (get categories code))

(defn get-lunchs-profit
  [struct-categories]
  (get struct-categories (get categories \A) 0))

(defn update-struct-categories
  "Update struct-categories adding 0 to non-found categories in input data, and sort them by profit"
  [struct-categories]
  (as-> (vals categories) $
    (zipmap $ (repeat 0))
    (merge $ struct-categories)
    (group-by val $)
    (sort $)))

(defn get-profit-category
  [struct-categories type-profit]
  (let [cats (val (type-profit struct-categories))]
    (if (< 1 (count cats))
      "EMPATE"
      (first (first cats)))))

(defn profit-average
  [struct-categories]
  (/ (apply + (keys struct-categories)) (count (keys categories))))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (loop [line (next-line reader)
             struct-categories {}]
        (when-not (nil? line)
          (let [[code profits] (get-fields line)
                code (str2char code)]
            (if-not (end-of-day code profits)
              (recur (next-line reader)
                (assoc-in struct-categories [(get-code-of-categories code)] (str2float profits)))
              (do
                (let [struct-categories-by-profit (update-struct-categories struct-categories)
                      max-profit-category (get-profit-category struct-categories-by-profit (:max profit))
                      min-profit-category (get-profit-category struct-categories-by-profit (:min profit))
                      sales-average (profit-average struct-categories-by-profit)]
                  (printf "%s#%s#%s\n"
                    max-profit-category
                    min-profit-category
                    (if (> (get-lunchs-profit struct-categories) sales-average) "SI" "NO")))
                (recur (next-line reader) {})))))))))