(ns unusual-sort.core
  (:gen-class))

(defn char-of
  [character]
  (.charAt (str character) 0))

(defn unusual-sort
  [& chars]
  (let [sub-ascii (zipmap (map char (flatten (list (range 65 91) (range 97 123) (range 48 58)))) (range))]
    (sort
      #(compare
         (get sub-ascii (char-of %1))
         (get sub-ascii (char-of %2)))
      chars)))
