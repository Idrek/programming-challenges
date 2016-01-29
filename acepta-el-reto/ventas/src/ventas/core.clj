(ns ventas.core
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pp])
  (:gen-class))

(def WORK-DAYS-PER-WEEK 6)
(def WEEKDAYS ["MARTES" "MIERCOLES" "JUEVES" "VIERNES" "SABADO" "DOMINGO"])

(defn create-week
  "Create a vector of vectors, associating WEEKDAYS with their profits"
  [reader]
  (as-> WORK-DAYS-PER-WEEK $
    (take $ (line-seq reader))
    (map #(Float/parseFloat %) $)
    (map vector $ WEEKDAYS)))

(defn -main
  ([] (-main *in*))
  ([input-file]
    (with-open [reader (io/reader input-file)]
      (loop []
        (let [week (create-week reader)
              sales? (not= -1 (Math/round (first (first week))))]
          (when-not (false? sales?)
            (let [week-sorted (sort week)
                  week-profits-mean (Float/parseFloat (pp/cl-format nil "~,2F" (/ (apply + (map first week)) (count week))))
                  sunday-profits (first (last week))]
              (println
                (second (first week-sorted))
                (second (last week-sorted))
                (cond
                  (< week-profits-mean sunday-profits) "SI"
                  (> week-profits-mean sunday-profits) "NO"
                  :else "EMPATE"))
              (recur))))))))

