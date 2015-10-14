(ns holes-in-text.core-test
  (:require [clojure.test :refer :all]
            [holes-in-text.core :refer :all]))

(deftest holes-in-text
  (testing "map-letter-holes"
    (is (= {\A 1, \B 2, \C 0, \D 1,
            \E 0, \F 0, \G 0, \H 0,
            \I 0, \J 0, \K 0, \L 0,
            \M 0, \N 0, \O 1, \P 1,
            \Q 1, \R 1, \S 0, \T 0,
            \U 0, \V 0, \W 0, \X 0,
            \Y 0, \Z 0}
          (map-letter-holes {#{\C \E \F \G \H \I \J \K \L \M \N \S \T \U \V \W \X \Y \Z} 0
                             #{\A \D \O \P \Q \R} 1
                             #{\B} 2
                             }))))

  (testing "get-number-of-holes"
    (def letter-holes {\A 1, \B 2, \C 0, \D 1,
                       \E 0, \F 0, \G 0, \H 0,
                       \I 0, \J 0, \K 0, \L 0,
                       \M 0, \N 0, \O 1, \P 1,
                       \Q 1, \R 1, \S 0, \T 0,
                       \U 0, \V 0, \W 0, \X 0,
                       \Y 0, \Z 0})
    (is (= 2 (get-number-of-holes letter-holes "CODECHEF")))
    (is (= 5 (get-number-of-holes letter-holes "DRINKEATCODE")))))
