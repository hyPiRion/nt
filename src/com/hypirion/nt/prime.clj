(ns com.hypirion.nt.prime
  (:require [com.hypirion.primes :as p]
            [clojure.math.numeric-tower :refer [expt]]))

(defn gcd [a b]
  (if (zero? b) a
      (recur b (mod a b))))

(defn lcm [a b]
  (* b (/ a (gcd a b))))

(defn extended-gcd
  "Calculates the extended GCD and returns a vector [r x y]. r is the GCD x is
  the multiplicative inverse of a modulo b, and y is the multiplicative inverse
  of b modulo a."
  [a b]
  (if (zero? b) [a 1 0]
    (let [q (quot a b)
          r (mod a b)
          [gcd x y] (extended-gcd b r)]
      [gcd y (- x (* q y))])))

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
  (acc-tot d (p/primes)))
