(ns com.hypirion.nt.test.digits
  (:require [simple-check.core       :as sc]
            [simple-check.generators :as gen]
            [simple-check.properties :as prop]
            [simple-check.clojure-test :as ct :refer [defspec]]
            [com.hypirion.nt.digits :refer :all]
            [clojure.test :refer :all]))

(defspec num-digits-conversion
  10000
  (prop/for-all [n gen/int]
    (= n (digits->num (num->digits n)))))

(defspec num-digits-conversion-w-radix
  10000
  (prop/for-all [r (gen/choose 1 1000000) n gen/int]
    (= n (digits->num (num->digits n r) r))))

(defspec digits-num-conversion
  10000
  (prop/for-all [digits (gen/vector (gen/choose 1 9) 1 18)]
    (= digits (num->digits (digits->num digits)))))

(defn largest-vector-size [radix]
  (loop [n Long/MAX_VALUE
         iters 0]
    (if (zero? n)
      (dec iters)
      (recur (quot n radix)
             (inc iters)))))

(defspec digits-num-conversion-w-radix
  10000
  (prop/for-all [[r digits]
                 (gen/bind (gen/choose 1 1000000)
                           (fn [r]
                             (gen/tuple (gen/return r)
                                        (gen/vector (gen/choose 1 r)
                                                    1 (largest-vector-size r)))))]
    (= digits (num->digits (digits->num digits r) r))))
