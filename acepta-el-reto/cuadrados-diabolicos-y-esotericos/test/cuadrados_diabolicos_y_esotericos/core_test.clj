(ns cuadrados-diabolicos-y-esotericos.core-test
  (:require [clojure.test :refer :all]
            [cuadrados-diabolicos-y-esotericos.core :refer :all]))

(deftest cuadrados-diabolicos-y-esotericos

  (testing "sum-rows-cells"
    (is (= (list (+ 0 1) (+ 2 3)) (sum-rows-cells [[0 1] [2 3]])))
    (is (= (list (+ 0 1 2) (+ 3 4 5) (+ 6 7 8)) (sum-rows-cells [[0 1 2] [3 4 5] [6 7 8]])))
    (is (= (list (+ 0 1 2 3) (+ 4 5 6 7) (+ 8 9 10 11) (+ 12 13 14 15)) (sum-rows-cells [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]]))))

  (testing "sum-columns-cells"
    (is (= (list (+ 0 2)) (sum-columns-cells [[0 1] [2 3]] [0 0])))
    (is (= (list (+ 0 3)) (sum-columns-cells [[0 1] [2 3]] [0 1])))
    (is (= (list (+ 1 2)) (sum-columns-cells [[0 1] [2 3]] [1 0])))
    (is (= (list (+ 1 3)) (sum-columns-cells [[0 1] [2 3]] [1 1])))
    (is (= (list (+ 0 4 8)) (sum-columns-cells [[0 1 2] [3 4 5] [6 7 8]] [0 1 2])))
    (is (= (list (+ 3 6 9 12)) (sum-columns-cells [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]] [3 2 1 0]))))

  (testing "check-natural-number-cells"
    (are [matrix] (= true (check-natural-number-cells matrix))
      [[1 2] [3 4]]
      [[3 2 1] [4 5 6] [9 7 8]]
      [[2 12 4 14] [9 11 16 3] [15 10 1 6] [5 7 13 8]])
    (are [matrix] (= false (check-natural-number-cells matrix))
      [[0 1] [2 3]]
      [[1 2] [3 5]]
      [[2 3] [4 5]]
      [[100 101 102] [103 104 105] [106 107 108]]))

  (testing "is-diabolical"
    (are [matrix] (= nil (is-diabolical matrix))
      [[0 1] [2 3]]
      [[0 1 2] [3 4 5] [6 7 8]])
    ;; Also esoteric
    (is (= 15 (is-diabolical [[4 9 2] [3 5 7] [8 1 6]])))
    ;; Also esoteric
    (is (= 34 (is-diabolical [[16 3 2 13] [5 10 11 8] [9 6 7 12] [4 15 14 1]])))
    ;; Only diabolical
    (is (= 75 (is-diabolical [[28 21 26] [23 25 27] [24 29 22]]))))

  (testing "esoteric-sides-cells-odd-matrix"
    (is (= (+ 1 3 5 7) (esoteric-sides-cells-odd-matrix [[0 1 2] [3 4 5] [6 7 8]])))
    (is (= (+ 2 10 14 22) (esoteric-sides-cells-odd-matrix [[0 1 2 3 4] [5 6 7 8 9] [10 11 12 13 14] [15 16 17 18 19] [20 21 22 23 24]]))))

  (testing "esoteric-central-cell-odd-matrix"
    (is (= (* 4 100) (esoteric-central-cell-odd-matrix [[0 1 2] [3 100 5] [6 7 8]])))
    (is (= (* 4 12) (esoteric-central-cell-odd-matrix [[0 1 2 3 4] [5 6 7 8 9] [10 11 12 13 14] [15 16 17 18 19] [20 21 22 23 24]]))))

  (testing "esoteric-edge-cells"
    (is (= (+ 0 2 6 8) (esoteric-edge-cells [[0 1 2] [3 100 5] [6 7 8]])))
    (is (= (+ 0 4 20 24) (esoteric-edge-cells [[0 1 2 3 4] [5 6 7 8 9] [10 11 12 13 14] [15 16 17 18 19] [20 21 22 23 24]]))))

  (testing "esoteric-central-cell-even-matrix"
    (is (= (+ 5 6 9 10) (esoteric-central-cell-even-matrix [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]]))))

  (testing "esoteric-sides-cells-even-matrix"
    (is (= (int (/ (+ 1 2 4 7 8 11 13 14) 2)) (esoteric-sides-cells-even-matrix [[0 1 2 3] [4 5 6 7] [8 9 10 11] [12 13 14 15]]))))

  (testing "is-esoteric"
    (is (= 20 (is-esoteric [[4 9 2] [3 5 7] [8 1 6]])))
    (is (= 34 (is-esoteric [[16 3 2 13] [5 10 11 8] [9 6 7 12] [4 15 14 1]])))))
