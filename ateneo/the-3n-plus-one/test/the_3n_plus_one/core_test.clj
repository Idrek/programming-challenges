(ns the-3n-plus-one.core-test
  (:require [clojure.test :refer :all]
            [the-3n-plus-one.core :refer :all]))

(deftest test-cycle-length
  (testing "cycle-length"
    ;; 1
    (is (= 1 (cycle-length 1)))
    ;; 2
    (is (= 2 (cycle-length 2)))
    ;; 3 10 5 16 8 4 2 1
    (is (= 8 (cycle-length 3)))
    ;; 4 2 1
    (is (= 3 (cycle-length 4)))
    ;; 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1
    (is (= 16 (cycle-length 22)))))
