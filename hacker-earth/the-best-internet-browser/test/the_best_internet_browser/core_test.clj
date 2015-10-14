(ns the-best-internet-browser.core-test
  (:require [clojure.test :refer :all]
            [the-best-internet-browser.core :refer :all]))

(deftest test-the-best-internet-browser

  (testing "jhool-ratio-characters"
    (is (= 7 (jhool-typed-characters "www.google.com")))
    (is (= 11 (jhool-typed-characters "www.hackerearth.com"))))

  (testing "standard-browser-ratio-characters"
    (is (= 14 (standard-browser-typed-characters "www.google.com")))
    (is (= 19 (standard-browser-typed-characters "www.hackerearth.com")))))
