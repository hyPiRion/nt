(ns com.hypirion.nt.test.digits
  (:require [simple-check.core       :as sc]
            [simple-check.generators :as gen]
            [simple-check.properties :as prop]
            [simple-check.clojure-test :as ct :refer [defspec]]
            [com.hypirion.nt.digits :refer :all]
            [clojure.test :refer :all]))

(defspec num-digits-conversion
  1000
  (prop/for-all [n gen/int]
    (= n (digits->num (num->digits n)))))

(defspec digits-num-conversion
  1000
  (prop/for-all [digits (gen/vector (gen/choose 1 9) 1 18)]
    (= digits (num->digits (digits->num digits)))))

