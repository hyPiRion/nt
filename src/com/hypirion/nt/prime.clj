(ns com.hypirion.nt.prime
  (require [com.hypirion.prime :as p]))

(defn divisor-count [n]
  (->> n p/factorize frequencies keys (map inc) (reduce *)))

(defn unique-prime-factors [n]
  (set (p/factorize n)))

(defn radical [n]
  (reduce * (unique-prime-factors n)))
