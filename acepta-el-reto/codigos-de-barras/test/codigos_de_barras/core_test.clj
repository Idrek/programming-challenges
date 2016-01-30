(ns codigos-de-barras.core-test
  (:require [clojure.test :refer :all]
            [codigos-de-barras.core :refer :all]))

(deftest a-test
  (testing "format-ean"
    (is (= "00000001" (format-ean "1")))
    (is (= "00000123" (format-ean "123")))
    (is (= "01234567" (format-ean "1234567")))
    (is (= "65839522" (format-ean "65839522")))
    (is (= "0000465839522" (format-ean "465839522")))
    (is (= "0123465839522" (format-ean "123465839522")))
    (is (= "789123465839522" (format-ean "789123465839522"))))

  (testing "check-ean-digits"
    (is (= true (check-ean-digits "65839522")))
    (is (= false (check-ean-digits "65839521")))
    (is (= true (check-ean-digits "8414533043847")))
    (is (= false (check-ean-digits "5129365779425"))))

  (testing "max-ean-country-code"
    (let [ean-countries {"0" "EEUU", "380" "Bulgaria", "50" "Inglaterra", "539" "Irlanda", "560" "Portugal",
                         "70" "Noruega", "759" "Venezuela", "850" "Cuba", "890" "India"}]
      (is (= 3 (max-ean-country-code ean-countries)))))

  (testing "get-ean-country"
    (let [ean-countries {"0" "EEUU", "380" "Bulgaria", "50" "Inglaterra", "539" "Irlanda", "560" "Portugal",
                         "70" "Noruega", "759" "Venezuela", "850" "Cuba", "890" "India"}]
      (is (= "" (get-ean-country "123456789012" ean-countries)))
      (is (= "EEUU" (get-ean-country "01234567890123" ean-countries)))
      (is (= "Venezuela" (get-ean-country "759012345670123" ean-countries)))
      (is (= "Desconocido" (get-ean-country "45601237890123" ean-countries))))))

