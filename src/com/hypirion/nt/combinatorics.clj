(ns com.hypirion.nt.combinatorics)

(defn ncr [n r]
  (if (<= 0 r n)
    (let [r (min r (- n r))]
      (loop [res 1
             i 1 j n]
        (if (<= i r)
          (recur (/ (* res j) i) (inc i) (dec j))
          res)))
    0))
