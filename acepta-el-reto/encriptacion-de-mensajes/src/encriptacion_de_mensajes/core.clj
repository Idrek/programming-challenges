(ns encriptacion-de-mensajes.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(def base "Base letter used to calculate the shift in the alphabet" \P)
(def alphabet "Associate ASCII uppercase letters with numbers" (zipmap (map char (range 65 91)) (range)))

(defn letter-to-value
  "Return the number assigned to the letter in the alphabet, or -1 if not found. Take into
  account the lowercase versions"
  [letter]
  (or (get alphabet (Character/toUpperCase letter)) -1))

(def vowels
  "Return a set with the vowel positions in the alphabet"
  (into #{} (map (fn [k] (letter-to-value k)) (filter #(get #{\A \E \I \O \U} %) (keys alphabet)))))

(defn letter-shift
  "Return the shift in the alphabet between the letter and the base"
  [letter]
  (- (letter-to-value letter) (letter-to-value base)))

(defn count-ciphered-vowels
  "Count vowels in the text after being deciphered"
  [str]
  (let [alphabet-length (count alphabet)
        shift (letter-shift (first str))]
    (reduce #(if (and
                   (not= (letter-to-value %2) -1)
                   (contains? vowels (mod (- (+ (letter-to-value %2) alphabet-length) shift) alphabet-length)))
               (inc %1)
               %1)
      0
      str)))

(defn decipher-equals
  "Check if the text deciphered equals to a 'flag-word'"
  [str flag-word]
  (let [shift (letter-shift (first str))
        str (rest str)
        strs (zipmap flag-word str)]
    (and
      (= (count flag-word) (count str))
      (every? #(Character/isUpperCase %) str)
      (every? #(= (+ shift (letter-to-value (first %))) (letter-to-value (second %))) strs))))

(defn -main
  "https://www.aceptaelreto.com/problem/statement.php?id=102"
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (doseq [line (line-seq reader) :while (false? (decipher-equals line "FIN"))]
          (println (count-ciphered-vowels line))))))
