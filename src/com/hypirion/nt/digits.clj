(ns com.hypirion.nt.digits)

(defn num->digits
  "Converts the number n into a list of digits in radix r. r defaults to 10."
  ([n] (num->digits n 10))
  ([n r]
     (loop [m n
            acc ()]
       (if (zero? m)
         acc
         (recur (quot m r)
                (conj acc (rem m r)))))))

(defn digits->num
  "Converts a sequence of digits in radix r to an integer. r defaults to 10."
  ([coll] (digits->num coll 10))
  ([coll r]
     (reduce
      (fn [a b] (+ (* r a) b))
      0
      coll)))

(defn digital-sum
  "Returns the digital sum of the integer in radix r. r defaults to 10."
  ([n] (digital-sum n 10))
  ([n r]
      (loop [m n
             acc 0]
        (if (zero? m)
          acc
          (recur (quot m r)
                 (+ acc (rem m r)))))))

(defn bit-count
  "Counts the amount of bits in v."
  ([v]
    (bit-count v 0) )
  ([v c]
    (if (zero? v)
      c
      (recur (bit-and v (dec v)) (inc c)))))
