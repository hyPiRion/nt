(ns com.hypirion.nt.test.prime
  (:require [com.hypirion.primes :as p]
            [com.hypirion.nt.prime :refer :all]
            [clojure.test :refer :all]))

(deftest test-divisor-count
  (testing "that divisor-count returns the correct amount of divisors"
    (is (= 2 (divisor-count 2)))
    (is (= 4 (divisor-count 8)))
    (is (= 3 (divisor-count 9)))
    (is (= 729 (divisor-count 656100000000)))
    (is (= 12288 (divisor-count 10234565468127000))))
  (testing "that primes only divide 1 and themselves"
    (is (every? #(= 2 (divisor-count %)) (p/take 100)))))
