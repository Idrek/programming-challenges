(ns wilson-primes.core-test
  (:require [clojure.test :refer :all]
            [wilson-primes.core :refer :all]
            [clojure.set :as set]))

(deftest wilson-primes

  (testing "factorial"
    (is (= 1 (factorial 0)))
    (is (= 1 (factorial 1)))
    (is (= 2 (factorial 2)))
    (is (= 6 (factorial 3)))
    (is (= 24 (factorial 4))))

  (testing "is-wilson-prime"
    (are [result] (= true (is-wilson-prime result))
      1 5 13 563)
    (are [result] (= false (not-every? false? (map is-wilson-prime result)))
      (set/difference (set (range 1 565)) #{1 5 13 563}))))
