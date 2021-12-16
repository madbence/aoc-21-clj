(ns madbence.aoc-21.day-16
  (:require [clojure.string :refer [join]]))

(def hex->bin
  {\0 "0000"
   \1 "0001"
   \2 "0010"
   \3 "0011"
   \4 "0100"
   \5 "0101"
   \6 "0110"
   \7 "0111"
   \8 "1000"
   \9 "1001"
   \A "1010"
   \B "1011"
   \C "1100"
   \D "1101"
   \E "1110"
   \F "1111"})

(def ops
  {0 +
   1 *
   2 min
   3 max
   5 #(if (> %1 %2) 1 0)
   6 #(if (< %1 %2) 1 0)
   7 #(if (= %1 %2) 1 0)})

(defn parse-field [bits offset length]
  (Integer/parseInt (join (take length (drop offset bits))) 2))

(defn parse-literal [bits offset]
  (loop [offset offset
         groups []]
    (let [group (->> bits (drop offset) (take 5))
          groups' (conj groups group)]
      (if (= (first group) \0)
        {:length (->> groups' (map count) (reduce + 6))
         :value (Long/parseLong (->> groups' (map rest) (apply concat) join) 2)}
        (if (> offset 10000) nil (recur (+ offset 5)
                                        groups'))))))

(declare parse-packet)

(defn parse-operator-by-length [bits offset]
  (let [n (parse-field bits offset 15)]
    {:length (+ 22 n)
     :packets (loop [offset' (+ offset 15)
                     packets []]
                 (if (>= offset' (+ offset n 15))
                   packets
                   (let [packet (parse-packet bits offset')]
                     (recur (+ offset' (:length packet))
                            (conj packets packet)))))}))

(defn parse-operator-by-count [bits offset]
  (let [n (parse-field bits offset 11)]
    (loop [offset (+ offset 11)
           packets []]
      (if (>= (count packets) n)
        {:length (+ 18 (->> packets (map :length) (reduce +)))
         :packets packets}
        (let [packet (parse-packet bits offset)]
          (recur (+ offset (:length packet))
                 (conj packets packet)))))))

(defn parse-packet [bits offset]
  (let [version (parse-field bits offset 3)
        type (parse-field bits (+ offset 3) 3)]
    (merge {:version version :type type}
           (if (= type 4)
             (parse-literal bits (+ offset 6))
             (let [mode (parse-field bits (+ offset 6) 1)]
               (if (= mode 0)
                 (parse-operator-by-length bits (+ offset 7))
                 (parse-operator-by-count bits (+ offset 7))))))))

(defn decode-packet [hex]
  (parse-packet (mapcat #(get hex->bin %) hex) 0))

(defn sum-versions [packet]
  (->> packet :packets (map sum-versions) (reduce + (:version packet))))

(defn evaluate-packet [packet]
  (let [t (:type packet)]
    (if (= t 4)
      (:value packet)
      (->> (:packets packet) (map evaluate-packet) (reduce (get ops t))))))

(defn a [input]
  (sum-versions (decode-packet input)))

(defn b [input]
  (evaluate-packet (decode-packet input)))
