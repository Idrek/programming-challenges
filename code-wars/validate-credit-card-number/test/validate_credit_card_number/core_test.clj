(ns validate-credit-card-number.core-test
  (:require [clojure.test :refer :all]
            [validate-credit-card-number.core :refer :all]))

(deftest test-validate-credit-card-number
  (testing "Invalid credit card numbers"
    (is (= false (validate-credit-card-number 4929358025759211)))
    (is (= false (validate-credit-card-number 4929358025759212))))
  (testing "Valid credit card numbers"
    (is (= true (validate-credit-card-number 4929358025759210)))
    (is (= true (validate-credit-card-number 5382169107028466)))
    (is (= true (validate-credit-card-number 6011678374807805)))))

(deftest test-double-digit?
  "If there are even number of digits, current one must be doubled, so return true"
  (testing "Even number of digits"
    (is (= false (double-digit? [0])))
    (is (= false (double-digit? [1 1 1])))
    (is (= false (double-digit? [4 5 6 7 9 4 2]))))
  (testing "Odd number of digits"
    (is (= true (double-digit? [0 0])))
    (is (= true (double-digit? [0 0 1 1])))
    (is (= true (double-digit? [0 1 2 3 4 5 6 7])))))

(deftest test-double-digits
  "If there are an even number of digits, double every other digit starting with the first, and
  if there are an odd number of digits, double every other digit starting with the second.
  If a resulting doubled number is greater than 9, replace it with either the sum of it's own digits"
  (testing "Even number of digits"
    (is (= 2724 (double-digits 1714))))
  (testing "Odd number of digits"
    (is (= 14385 (double-digits 12345)))
    (is (= 891 (double-digits 891)))))
