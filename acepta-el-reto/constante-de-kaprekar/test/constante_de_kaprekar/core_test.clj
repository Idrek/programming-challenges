(ns constante-de-kaprekar.core-test
  (:require [clojure.test :refer :all]
            [constante-de-kaprekar.core :refer :all]))

(deftest constante-de-kaprekar

  (testing "format-number"
    (is (= "0001" (format-number 1)))
    (is (= "0012" (format-number 12)))
    (is (= "0123" (format-number 123)))
    (is (= "1234" (format-number 1234)))
    (is (= "123456" (format-number 123456))))

  (testing "is-repdigit"
    (is (= true (is-repdigit 1111)))
    (is (= true (is-repdigit 4444)))
    (is (= false (is-repdigit 1112)))
    (is (= false (is-repdigit 1121)))
    (is (= false (is-repdigit 1211)))
    (is (= false (is-repdigit 2111)))
    (is (= false (is-repdigit 123)))
    (is (= false (is-repdigit 11)))
    (is (= false (is-repdigit 1))))

  (testing "is-kaprekar-constant"
    (is (= true (is-kaprekar-constant 6174)))
    (is (= false (is-kaprekar-constant 4716))))

  (testing "numbers-with-digits-ordered"
    (is (= '(4321 1234) (numbers-with-digits-ordered 1234)))
    (is (= '(4321 1234) (numbers-with-digits-ordered 3142)))
    (is (= '(4421 1244) (numbers-with-digits-ordered 4124)))
    (is (= '(1111 1111) (numbers-with-digits-ordered 1111)))
    (is (= '(2211 1122) (numbers-with-digits-ordered 2121)))
    (is (= '(3210 123) (numbers-with-digits-ordered 231)))
    (is (= '(1000 1) (numbers-with-digits-ordered 1))))

  (testing "kaprekar-iterations"
    (is (= 0 (kaprekar-iterations 6174)))
    (are [number] (= 8 (kaprekar-iterations number))
      0000 1111 2222 3333 4444 5555 6666 7777 8888 9999)
    (is (= 3 (kaprekar-iterations 3524)))
    (is (= 5 (kaprekar-iterations 1121)))
    (is (= 7 (kaprekar-iterations 1893)))))
