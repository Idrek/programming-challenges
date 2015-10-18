(ns encriptacion-de-mensajes.core-test
  (:require [clojure.test :refer :all]
            [encriptacion-de-mensajes.core :refer :all]))


(deftest encriptacion-de-mensajes

  (testing "letter-to-value"
    (is (= 0 (letter-to-value \A)))
    (is (= 25 (letter-to-value \Z)))
    (is (= 0 (letter-to-value \a)))
    (is (= 25 (letter-to-value \z)))
    (is (= 3 (letter-to-value \d)))
    (is (= 6 (letter-to-value \g)))
    (is (= -1 (letter-to-value \space)))
    (is (= -1 (letter-to-value 6)))
    (is (= -1 (letter-to-value \6)))
    (is (= -1 (letter-to-value \.)))
    (is (= -1 (letter-to-value \?))))

  (testing "letter-shift"
    (is (= 2 (letter-shift \R)))
    (is (= 2 (letter-shift \r)))
    (is (= -3 (letter-shift \M)))
    (is (= -3 (letter-shift \m))))

  (testing "vowels"
    (is (= #{0 20 4 14 8} vowels)))

  (testing "count-ciphered-vowels"
    (is (= 10 (count-ciphered-vowels "qbfjpvBFJPV")))
    (is (= 4 (count-ciphered-vowels "xXzwoziui-Um"))))

  (testing "decipher-equals"
    (is (= false (decipher-equals "pfin" "FIN")))
    (is (= true (decipher-equals "qGJO" "FIN")))))
