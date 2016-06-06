(ns validate-credit-card-number.core
  (:require [numberto.converters :as nc]
            [numberto.math :as nm])
  (:gen-class))

(defn double-digit?
  [digits]
  (= 0 (bit-and 1 (count digits))))

(defn double-digits
  [n]
  (loop [digits (nc/num->digits n)
         result []]
    (if (seq digits)
      (let [digit-mod (* (first digits) (if (double-digit? digits) 2 1))
            digit-mod-normalized (if (> digit-mod 9) (nm/sum-of-digits digit-mod) digit-mod)]
        (recur (rest digits) (conj result digit-mod-normalized)))
      (nc/digits->num result))))

(defn validate-credit-card-number
  [cc]
  (as-> (double-digits cc) $
    (nm/sum-of-digits $)
    (rem $ 10)
    (= 0 $)
    (or (and $ true) false)))

(defn -main
  [& args]
  (and (validate-credit-card-number (first args)) (println "Valid")))