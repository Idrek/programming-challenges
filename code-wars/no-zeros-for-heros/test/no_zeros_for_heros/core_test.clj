(ns no-zeros-for-heros.core-test
  (:require [clojure.test :refer :all]
            [no-zeros-for-heros.core :refer :all]))

(deftest test-no-boring-zeros
  (testing "One ending zero"
    (is (= 145 (no-boring-zeros 1450))))
  (testing "Several ending zeros"
    (is (= 96 (no-boring-zeros 960000))))
  (testing "Zeros in middle positions"
    (is (= 105 (no-boring-zeros 1050)))
    (is (= 100506 (no-boring-zeros 10050600))))
  (testing "Negative number"
    (is (= -96 (no-boring-zeros -960000)))))
