(ns dont-drink-the-water.core
  (:gen-class))

(def density-chart {:H 1.36, :W 1.00 :A 0.87 :O 0.80})

(defn separate-liquids
  [glass]
  (let [density-chart (into {} (sort-by val density-chart))
        dims (butlast (map count glass))]
    (cond
      (empty? glass) glass
      :else
      (as-> glass $
        (flatten $)
        (sort-by #((keyword (str %)) density-chart) $)
        (loop [dims dims
               result $]
          (if (empty? dims)
            (if (seq? (first result)) result (vector result))
            (let [arr (if (= result $)
                        (split-at (first dims) result)
                        (apply conj (vec (butlast result)) (split-at (first dims) (last result))))]
              (recur (rest dims) arr))))))))

(defn -main
  ""
  [& args])
