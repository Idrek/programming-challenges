(ns diagonal-difference.core-test
  (:require [clojure.test :refer :all]
            [diagonal-difference.core :refer :all]))

(deftest diagonal-difference

  (testing "get-columns-sum"
    ;; First colum
    (is (= (+ 0 3 6) (get-columns-sum [[0 1 2] [3 4 5] [6 7 8]] [0 0 0])))
    ;; Diagonal from left to right
    (is (= (+ 0 4 8) (get-columns-sum [[0 1 2] [3 4 5] [6 7 8]] [0 1 2])))
    ;; Diagonal from right to left
    (is (= (+ 2 4 6) (get-columns-sum [[0 1 2] [3 4 5] [6 7 8]] [2 1 0]))))

  (testing "get-diagonal-difference"
    (is (= (- (+ 0 4 8) (+ 2 4 6)) (get-diagonal-difference [[0 1 2] [3 4 5] [6 7 8]])))
    (is (= (- (+ 0 4 8) (+ 2 4 6)) (get-diagonal-difference [[0 1 2] [3 4 5] [6 7 8]] 3)))
    (is (=
          (- (+ 0 6 12 18 24) (+ 4 8 12 16 20))
          (get-diagonal-difference [[0 1 2 3 4] [5 6 7 8 9] [10 11 12 13 14] [15 16 17 18 19] [20 21 22 23 24]])))
    (is (=
          (- (+ 0 6 12 18 24) (+ 4 8 12 16 20))
          (get-diagonal-difference [[0 1 2 3 4] [5 6 7 8 9] [10 11 12 13 14] [15 16 17 18 19] [20 21 22 23 24]] 5)))))
