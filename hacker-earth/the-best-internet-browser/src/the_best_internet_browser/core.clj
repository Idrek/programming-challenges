(ns the-best-internet-browser.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(def vowels #{\A \E \I \O \U})

(defn jhool-ratio-characters
  "Return the ratio of characters to type in the amazing Jhool web browser:
    - Never 'www.'
    - Always literally '.com'
    - No vowels for the rest."
  [url]
  (let [domain-length (count ".com")]
    (+ (count (filter #(nil? (get vowels %)) (.toUpperCase (subs url domain-length (- (count url) domain-length))))) domain-length)))

(defn standard-browser-ratio-characters
  "Return the ratio of characters to type in standard web browsers"
  [url]
  (count url))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (let [num-inputs (Integer. (first (line-seq reader)))]
        (doseq [url (take num-inputs (line-seq reader))]
          (printf "%d/%d\n" (jhool-ratio-characters url) (standard-browser-ratio-characters url)))))))
