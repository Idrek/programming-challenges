(ns unusual-sort.core-test
  (:require [clojure.test :refer :all]
            [unusual-sort.core :refer :all]))

(deftest test-unusual-sort

  (testing "char-of"
    (is (= \a (char-of \a)))
    (is (= \a (char-of "a")))
    (is (= \1 (char-of \1)))
    (is (= \1 (char-of "1")))
    (is (= \1 (char-of 1))))

  (testing "unusual-sort"
    (is (= '(\a \b \z) (unusual-sort \a \z \b)))
    (is (= '(\a \b \z) (unusual-sort '(\a \z \b))))
    (is (= '(\a \b \z) (unusual-sort [\a \z \b])))

    (is (= '(\B \Z \a) (unusual-sort \a \Z \B)))
    (is (= '(\B \Z \a) (unusual-sort '(\a \Z \B))))
    (is (= '(\B \Z \a) (unusual-sort [\a \Z \B])))

    (is (= '(\a \z \1) (unusual-sort \1 \z \a)))
    (is (= '(\a \z \1) (unusual-sort '(\1 \z \a))))
    (is (= '(\a \z \1) (unusual-sort [\1 \z \a])))

    (is (= '(\Z \a \1) (unusual-sort \1 \Z \a)))
    (is (= '(\Z \a \1) (unusual-sort '(\1 \Z \a))))
    (is (= '(\Z \a \1) (unusual-sort [\1 \Z \a])))

    (is (= '(\a \b \z 1 2 3) (unusual-sort 3 2 1 \a \z \b)))
    (is (= '(\a \b \z 1 2 3) (unusual-sort '(3 2 1 \a \z \b))))
    (is (= '(\a \b \z 1 2 3) (unusual-sort [3 2 1 \a \z \b])))

    (is (= '(\a \b \c 1 \2 3)) (unusual-sort 3 \2 1 \a \c \b))
    (is (= '(\a \b \c 1 \2 3)) (unusual-sort '(3 \2 1 \a \c \b)))
    (is (= '(\a \b \c 1 \2 3)) (unusual-sort [3 \2 1 \a \c \b]))))
