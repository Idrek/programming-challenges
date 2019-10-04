(ns el-juego-de-la-linterna.core
  (:require [clojure.java.io :as clj-io]
            [clojure.string :as clj-str])
  (:gen-class))

(defn next-line [reader]
  (-> reader line-seq first))

(defn str->ints [s]
  (mapv #(Integer. %) (clj-str/split s #"\s+")))

(defn parse-line [reader]
  (some-> reader next-line str->ints))

(defn read-next-n-lines [reader n]
  (repeatedly n #(parse-line reader)))

(defn jimmy-plays [nephew-heights]
  (loop [lowest (inc (Math/pow 10 9))
         highest 0
         [height heights] ((juxt first rest) nephew-heights)]
    (cond
      (< lowest height highest) false
      (empty? heights) true
      :else (recur (if (< height lowest) height lowest)
             (if (< highest height) height 0)
             ((juxt first rest) heights)))))

(defn flashlight-play [reader]
  (loop [[num-nephews-line
          nephew-heights-line] (read-next-n-lines reader 2)
         prizes []]
    (if (nil? num-nephews-line)
      prizes
      (let [num-nephews (first num-nephews-line)
            nephew-heights (take num-nephews nephew-heights-line)]
        (recur (read-next-n-lines reader 2)
               (conj prizes (jimmy-plays nephew-heights)))))))

(defn flashlight-game [input-file]
  (try
    (with-open [reader (clj-io/reader input-file)]
      (flashlight-play reader))
    (catch Exception e
      (.getMessage e))))

(defn print-msg [cases]
  (let [msg #(if % "SIEMPRE PREMIO" "ELEGIR OTRA")]
    (doseq [case cases]
      (prn (msg case)))))

(defn -main
  "https://www.aceptaelreto.com/problem/statement.php?id=384"
  ([] (-main *in*))
  ([input-file]
   (-> input-file
       flashlight-game
       print-msg)))
