(ns count-most-frequent-item-in-array.core-test
  (:require [clojure.test :refer :all]
            [count-most-frequent-item-in-array.core :refer :all]))

(deftest test-count-most-frequent-item-in-array
  (testing "test-count-most-frequent-item-in-array"
    (is (= 0 (count-most-frequent-item [])))
    (is (= 1 (count-most-frequent-item [1 0 2])))
    (is (= 2 (count-most-frequent-item [2 2 1])))
    (is (= 5 (count-most-frequent-item [3, -1, -1, -1, 2, 3, -1, 3, -1, 2, 4, 9, 3])))))
