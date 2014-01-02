(ns com.hypirion.nt.combinatorics)

(defn !
  "Calculates the factorial of n, n!."
  [n]
  (loop [i 2, acc 1]
    (if (< n i)
      acc
      (recur (inc i) (* acc i)))))

(defn ncr
  "Calculates n choose r."
  [n r]
  (if (<= 0 r n)
    (let [r (min r (- n r))]
      (loop [res 1
             i 1 j n]
        (if (<= i r)
          (recur (/ (* res j) i) (inc i) (dec j))
          res)))
    0))

(defn- to-radix [n radix] ; in reverse
  (loop [i n
         acc []]
    (if (zero? i)
      acc
      (recur (quot i radix)
             (conj acc (rem i radix))))))

(defn lucas-thm
  "Calculates m choose n (mod p)."
  [m n p]
  (reduce
   (fn [acc [a b]]
     (-> (ncr a b) (* acc) (mod p)))
   1
   (mapv list (to-radix m p) (to-radix n p))))

(defn nth-string
  "Returns the nth string, i.e. nth subset with repeating elements.

  The strings are ordered by the size of the string, then the elements'
  indices."
  [vect n]
  (let [len (count vect)]
    (loop [i n
           accum ()]
      (if (neg? i)
        accum
        (recur (dec (quot i len))
               (->> (mod i len) (get vect) (conj accum)))))))
