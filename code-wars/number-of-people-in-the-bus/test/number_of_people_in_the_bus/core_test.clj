(ns number-of-people-in-the-bus.core-test
  (:require [clojure.test :refer :all]
            [number-of-people-in-the-bus.core :refer :all]))

(deftest number-of-people-in-the-bus
  (testing "people-in-bus"
    (is (= 0 (people-in-bus [[0 0]])))
    (is (= 0 (people-in-bus[[2 0] [0 2]])))
    (is (= 1 (people-in-bus[[3 0] [2 1] [5 8]])))
    (is (= 6 (people-in-bus[[1 0] [3 0] [2 0]])))
    (is (= 4 (people-in-bus[[4 0] [3 3] [16 16]])))))

