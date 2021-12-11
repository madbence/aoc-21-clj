(ns madbence.aoc-21.day-10
  (:require [clojure.string :refer [split-lines]]))

(defn get-score [line]
  (loop [line line
         stack '()]
    (let [c (first line)
          s (first stack)]
      (if (nil? c)
        0
        (case c
          (\( \[ \{ \<) (recur (rest line) (cons c stack))
          \) (if (not= s \() 3 (recur (rest line) (rest stack)))
          \] (if (not= s \[) 57 (recur (rest line) (rest stack)))
          \} (if (not= s \{) 1197 (recur (rest line) (rest stack)))
          \> (if (not= s \<) 25137 (recur (rest line) (rest stack))))))))

(defn get-score' [line]
  (->> line
       (reduce #(case %2
                  (\( \[ \{ \<) (cons %2 %1)
                  (\) \] \} \>) (rest %1)) '())
       (reduce #(+ (* 5 %1) (case %2 \( 1 \[ 2 \{ 3 \< 4)) 0)))

(defn get-middle [scores]
  (-> scores sort (nth (quot (count scores) 2))))

(defn a [input]
  (->> input
       split-lines
       (map get-score)
       (reduce +)))

(defn b [input]
  (->> input
       split-lines
       (filter #(zero? (get-score %)))
       (map get-score')
       get-middle))
