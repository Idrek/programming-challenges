(ns codigos-de-barras.core
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [clojure.string :as str])
  (:gen-class))

(defn format-ean
  "Fill with zeros to fix an EAN with incorrect number of digits, either EAN-8 or EAN-13"
  [ean]
  (let [ean-formats {:ean8 8 :ean13 13}]
    (letfn [(fill-with-zeroes [ean width]
              (pp/cl-format nil (str "~" width ",'0d") ean))]
      (condp >= (count ean)
        (:ean8 ean-formats) (fill-with-zeroes ean 8)
        (:ean13 ean-formats) (fill-with-zeroes ean 13)
        ean))))

(defn check-ean-digits
  "Calculate if control digit of EAN is correct. Returns true of false"
  [ean]
  (let [ean-calculation
        (reduce
          (fn [acc [index digit]]
            (if (= 1 (bit-and 1 index))
               (+ acc (* digit 3))
               (+ acc digit)))
           0
          (map vector (range) (map #(Character/digit % 10) (reverse ean))))]
    (if (= 0 (bit-and 1 (mod ean-calculation 10))) true false)))

(defn max-ean-country-code
  "Max length of all country codes"
  [ean-countries]
  (as-> (keys ean-countries) $
    (map #(count (.toString %)) $)
    (apply max $)))

(defn get-ean-country
  "Get the country of EAN based in its first digits. Only for EAN-13"
  [ean ean-countries]
  (if (< (count ean) 13)
    ""
    (loop [sub-ean (subs ean 0 (max-ean-country-code ean-countries))]
      (cond
        (get ean-countries sub-ean) (get ean-countries sub-ean)
        (empty? sub-ean) "Desconocido"
        :else (recur (subs sub-ean 0 (dec (count sub-ean))))))))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [ean (line-seq reader) :while (not= ean "0")]
        (let [ean? (check-ean-digits ean)
              ean-countries {"0" "EEUU", "380" "Bulgaria", "50" "Inglaterra", "539" "Irlanda", "560" "Portugal",
                             "70" "Noruega", "759" "Venezuela", "850" "Cuba", "890" "India"}]
          (println
            (if ean? "SI" "NO")
            (or (and ean? (get-ean-country ean ean-countries)) "")))))))
