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

(defn- to-radix [n radix] ; in reverse
  (loop [i n
         acc []]
    (if (zero? i)
      acc
      (recur (quot i radix)
             (conj acc (rem i radix))))))

(defn lucas-thm [n m p]
  (reduce
   (fn [acc [a b]]
     (-> (ncr a b) (* acc) (mod p)))
   1
   (map list (to-radix n p) (to-radix m p))))
