(ns next-bigger-number-with-same-digits.core-test
  (:require [clojure.test :refer :all]
            [next-bigger-number-with-same-digits.core :refer :all]))

(deftest test-next-bigger-number-with-same-digits

  (testing "different-numbers-with-same-digits"
    (is (= '(1) (different-numbers-with-same-digits 1)))
    (is (= '(1) (different-numbers-with-same-digits 0001)))
    (is (= '(11) (different-numbers-with-same-digits 11)))
    (is (= '(9) (different-numbers-with-same-digits 011))) ;; Octal
    (is (= '(111) (different-numbers-with-same-digits 111)))
    (is (= '(112 121 211) (sort (different-numbers-with-same-digits 121))))
    (is (= '(123 132 213 231 312 321) (sort (different-numbers-with-same-digits 123)))))

  (testing "next-bigger-number-with-same-digits"
    (is (= 21 (next-bigger-number-with-same-digits 12)))
    (is (= 531 (next-bigger-number-with-same-digits 513)))
    (is (= 2071 (next-bigger-number-with-same-digits 2017)))
    (is (= -1 (next-bigger-number-with-same-digits 9)))
    (is (= -1 (next-bigger-number-with-same-digits 111)))
    (is (= -1 (next-bigger-number-with-same-digits 531)))))
