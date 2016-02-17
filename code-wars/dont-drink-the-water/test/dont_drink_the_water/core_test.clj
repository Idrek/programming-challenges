(ns dont-drink-the-water.core-test
  (:require [clojure.test :refer :all]
            [dont-drink-the-water.core :refer :all]))

(deftest test-separate-liquids
  (testing "test-separate-liquids"
    (is (= ['(\O \O \O \O) '(\W \W \W \W) '(\H \H \H \H)]
          (separate-liquids [[\H , \H , \W , \O ],[\W ,\W ,\O ,\W ],[\H ,\H ,\O ,\O ]])))
    (is (= ['(\O \O \O \O) '(\A \A \A \A) '(\W \W \W \W) '(\H \H \H \H)]
          (separate-liquids [[\A ,\A ,\O ,\H ],[\A , \H , \W , \O ],[\W ,\W ,\A ,\W ],[\H ,\H ,\O ,\O ]])))
    (is (= ['(\O \A \W \H)] (separate-liquids [[\A \H \W \O]])))
    (is (= ['(\O) '(\A) '(\W) '(\H)] (separate-liquids [[\A],[\H],[\W],[\O]])))
    (is (= [] (separate-liquids [])))))
