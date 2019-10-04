(ns find-pair.core-test
  (:require [clojure.string :as clj-str]
            [clojure.test :refer :all]
            [find-pair.core :refer :all]))

(deftest test-find-pairs
  (doseq [mode [:naive :sorting :hashing]]
    (testing (format "%s mode\n" (-> mode name clj-str/capitalize))
      (testing "Empty collection"
        (let [result (find-pairs [] 10 mode)]
          (is (= result (vector)))
          (is (vector? result))))
      (testing "Collection with one element whose value is different of sum"
        (let [result (find-pairs [1] 10 :naive)]
          (is (= result (vector)))
          (is (vector? result))))
      (testing "Collection with one element whose value is equal to sum"
        (let [result (find-pairs [10] 10 :naive)]
          (is (= result (vector)))
          (is (vector? result))))
      (testing "Collection of two elements that does not sum"
        (let [result (find-pairs [1 2] 4 :naive)]
          (is (= result (vector)))
          (is (vector? result))))
      (testing "Collection of two elements that does sum"
        (let [result (find-pairs [1 3] 4 :naive)]
          (is (= result [[0 1]]))
          (is (vector? result))
          (is (every? vector? result))))
      (testing "Collection of three elements that does not sum"
        (let [result (find-pairs [1 2 3] 6 :naive)]
          (is (= result (vector)))
          (is (vector? result))))
      (testing "Collection of three elements that does sum"
        (let [result (find-pairs [1 2 3] 5 :naive)]
          (is (= result [[1 2]]))
          (is (vector? result))
          (is (every? vector? result))))
      (testing "Collection of more than 3 elements that does not sum"
        (let [result (find-pairs [8 7 2 5 3 1] 14 :naive)]
          (is (= result (vector)))
          (is (vector? result))))
      (testing "Two pairs that do sum"
        (let [result (find-pairs [8 7 2 5 3 1] 10 :naive)]
          (is (= result [[0 2] [1 4]]))
          (is (vector? result))
          (is (every? vector? result)))))))
