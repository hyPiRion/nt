(ns com.hypirion.nt.test.combinatorics
  (:require [simple-check.core       :as sc]
            [simple-check.generators :as gen]
            [simple-check.properties :as prop]
            [simple-check.clojure-test :as ct :refer [defspec]]
            [com.hypirion.nt.combinatorics :refer :all]
            [clojure.test :refer :all]))

(defn gen-string-of
  "Generates a string of type gen-type, which is not empty and contains distinct
  elements only."
  [gen-type]
  (->> (gen/vector gen-type) (gen/not-empty) (gen/fmap distinct)))

(defspec nth-string-lt-count
  1000
  (testing "nth-string(vect, i) = vect[i] for i < |vect|"
    (prop/for-all [[vect n]
                   (gen/bind (gen-string-of gen/int)
                             (fn [vect] (gen/tuple (gen/return vect)
                                                  (gen/choose 0 (dec (count vect))))))]
      (= (nth-string vect n) [(get vect n)]))))

(defn- nth-string-same-len [vect n m]
  (let [v1 (nth-string vect n)
        v2 (nth-string vect m)]
    (= (count v1) (count v2))))

(defspec nth-string-ascending
  1000
  (testing "nth-string(vect, i) < nth-string(vect, i+1) given same length"
    (prop/for-all [[n vect]
                   (gen/bind gen/pos-int
                     (fn [n] (gen/tuple (gen/return n)
                                (->> (gen-string-of gen/int)
                                     (gen/fmap sort)
                                     (gen/such-that #(nth-string-same-len % n (inc n)))))))]
      (let [v1 (vec (nth-string vect n))
            v2 (vec (nth-string vect (inc n)))]
        (= [v1 v2] (sort [v1 v2]))))))
