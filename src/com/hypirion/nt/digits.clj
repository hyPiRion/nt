(ns com.hypirion.nt.digits)

(defn num->digits
  "Converts the number n into a list of digits in base 10."
  [n]
  (loop [m n
         acc nil]
    (if (zero? m)
      acc
      (recur (quot m 10)
             (cons (rem m 10) acc)))))

(defn digits->num
  "Converts a sequence of digits in base 10 to an integer."
  [coll]
  (reduce
   (fn [a b] (+ (* 10 a) b))
   0
   coll))

(defn digital-sum
  "Returns the digital sum of the integer (in base 10)."
  [n]
  (loop [m n
         acc 0]
    (if (zero? m)
      acc
      (recur (quot m 10)
             (+ acc (rem m 10))))))

(defn bit-count
  "Counts the amount of bits in v."
  ([v]
    (bit-count v 0) )
  ([v c]
    (if (zero? v)
      c
      (recur (bit-and v (dec v)) (inc c)))))
