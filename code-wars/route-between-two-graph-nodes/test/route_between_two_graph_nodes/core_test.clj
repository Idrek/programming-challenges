(ns route-between-two-graph-nodes.core-test
  (:require [clojure.test :refer :all]
            [route-between-two-graph-nodes.core :refer :all]))

(deftest test-route-between-two-graph-nodes
  (testing "find-route-between-two-graph-nodes"
    (are [origin-node destination-node]
      (= true (find-route-between-two-graph-nodes {5 #{11}
                                                                7 #{8 11}
                                                                3 #{8 10}
                                                                11 #{2 9 10}
                                                                8 #{9}
                                                                9 #{}
                                                                10 #{}} origin-node destination-node))
      5 11
      5 2
      5 9
      5 10
      7 11
      7 8
      7 2
      7 9
      7 10
      3 8
      3 9
      3 10
      11 2
      11 9
      11 10
      8 9)
    (are [origin-node destination-node]
      (= false (find-route-between-two-graph-nodes {5 #{11}
                                                         7 #{8 11}
                                                         3 #{8 10}
                                                         11 #{2 9 10}
                                                         8 #{9}
                                                         9 #{}
                                                         10 #{}} origin-node destination-node))
      5 7
      5 3
      7 5
      7 3
      5 8
      3 2
      11 5
      11 7
      11 3
      11 8
      8 5
      8 7
      8 3
      8 10
      8 2
      2 9
      2 10
      2 11
      2 8
      2 5
      2 7
      2 3
      9 11
      9 8
      9 10
      10 3
      10 5
      10 9
      10 2
      10 11)))
