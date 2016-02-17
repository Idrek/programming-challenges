(ns did-she-say-hallo.core-test
  (:require [clojure.test :refer :all]
            [did-she-say-hallo.core :refer :all]))

(deftest test-validate-hello
  (testing "test-validate-hello"
    (is (= true (validate-hello "hello")))
    (is (= true (validate-hello "ciao bella!")))
    (is (= true (validate-hello "salut")))
    (is (= true (validate-hello "hallo, salut")))
    (is (= true (validate-hello "hombre! Hola!")))
    (is (= true (validate-hello "Hallo, wie geht's dir?")))
    (is (= true (validate-hello "AHOJ!")))
    (is (= true (validate-hello "czesc")))
    (is (= false (validate-hello "meh")))
    (is (= true (validate-hello "Ahoj")))))
