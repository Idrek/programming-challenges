(ns aproximacion-de-gauss.core
  (:require [com.hypirion.primes :as primes]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn how-many-primes-until
  [num]
  ;; Last 'inc' used to include '1' as prime too.
  (-> (inc num) (primes/take-below) (count) (inc)))

(defn gauss-error
  [num]
  (- (/ (how-many-primes-until num) num) (/ (Math/log num))))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (loop [line (first (line-seq reader))]
        (let [[gauss-num err] (map #(Integer/parseInt %) (str/split line #"\s+"))]
          (when (not= 0 gauss-num err)
            (let [gauss-aprox-error (gauss-error gauss-num)
                  err-allowed (/ (Math/pow 10 err))]
              (println
                (cond
                  (< (Math/abs gauss-aprox-error) err-allowed) "Menor"
                  (> (Math/abs gauss-aprox-error) err-allowed) "Mayor"
                  :else "Igual")))
            (recur (first (line-seq reader)))))))))
