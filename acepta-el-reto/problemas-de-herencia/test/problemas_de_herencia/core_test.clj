(ns problemas-de-herencia.core-test
  (:require [clojure.test :refer :all]
            [problemas-de-herencia.core :refer :all]))

(deftest problemas-de-herencia

  (testing "create-polynomial"
    (is (= [[1 1] [0 0]] (create-polynomial 1 [1 0])))
    (is (= [[3 1] [6 0]] (create-polynomial 1 [3 6])))
    (is (= [[3 2] [2 1] [1 0]] (create-polynomial 2 [3 2 1])))
    (is (= [[1 2] [5 1] [6 0]] (create-polynomial 2 [1 5 6])))
    (is (= [[4 3] [1 2] [4 1] [2 0]] (create-polynomial 3 [4 1 4 2]))))

  (testing "resolve-polynomial"
    (is (= (+ (* 1 (Math/pow 3 1)) (* 0 (Math/pow 3 0))) (resolve-polynomial [[1 1] [0 0]] 3)))
    (is (= (+ (* 3 (Math/pow 2 1)) (* 6 (Math/pow 2 0))) (resolve-polynomial [[3 1] [6 0]] 2)))
    (is (= (+ (* 3 (Math/pow 4 2)) (* 2 (Math/pow 4 1)) (* 1 (Math/pow 4 0))) (resolve-polynomial [[3 2] [2 1] [1 0]] 4)))
    (is (= (+ (* 1 (Math/pow 3 2)) (* 5 (Math/pow 3 1)) (* 6 (Math/pow 3 0))) (resolve-polynomial [[1 2] [5 1] [6 0]] 3)))
    (is (= (+ (* 4 (Math/pow 5 3)) (* 1 (Math/pow 5 2)) (* 4 (Math/pow 5 1)) (* 2 (Math/pow 5 0))) (resolve-polynomial [[4 3] [1 2] [4 1] [2 0]] 5))))

  (testing "inside-low-limit"
    (is (= false (inside-low-limit -0.1)))
    (is (= false (inside-low-limit -0.001)))
    (is (= false (inside-low-limit -1)))
    (is (= false (inside-low-limit -4)))
    (is (= true (inside-low-limit 0.001)))
    (is (= true (inside-low-limit 0.1)))
    (is (= true (inside-low-limit 0.1)))
    (is (= true (inside-low-limit 0.99)))
    (is (= true (inside-low-limit 1.001)))
    (is (= true (inside-low-limit 6)))))
