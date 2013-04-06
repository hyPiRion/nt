(ns com.hypirion.nt.prime
  (:require [com.hypirion.primes :as p]
            [clojure.math.numeric-tower :refer [expt]]))

(defn factor? [num den]
  (zero? (rem num den)))

(defn divisor-count [n]
  (->> n p/factorize frequencies vals (map inc) (reduce *)))

(defn unique-prime-factors [n]
  (set (p/factorize n)))

(defn radical
  "The radical of a number n is the product of distinct prime factors of n."
  [n]
  (reduce * (unique-prime-factors n)))

(defn- totient-pow [p k]
    (* (expt p (- k 1)) (- p 1)))

;; TODO: Make this completely tail recursive.
(defn- acc-tot [d primes]
  (cond (= 1 d) 1
        (p/prime? d) (- d 1)
        :else (loop [[p & r] primes]
                (if (zero? (rem d p))
                  (loop [k 1
                         d (quot d p)]
                    (if (zero? (rem d p))
                      (recur (inc k)
                             (quot d p))
                      (* (acc-tot d r)
                         (totient-pow p k))))
                  (recur r)))))

(defn euler-totient [d]
  (acc-tot d p/primes))
