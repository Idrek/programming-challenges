(ns stringy-strings.core-test
  (:require [clojure.test :refer :all]
            [stringy-strings.core :refer :all]))

(deftest test-stringy-strings
  (testing "test-stringy-strings"
    (is (= "" (stringy-strings 0)))
    (is (= "1" (stringy-strings 1)))
    (is (= "10" (stringy-strings 2)))
    (is (= "101" (stringy-strings 3)))
    (is (= "1010" (stringy-strings 4)))
    (is (= "101010" (stringy-strings 6)))
    (is (= "101010101010" (stringy-strings 12)))))
