(ns madbence.aoc-21.day-08
  (:require [clojure.string :refer [split-lines join]]
            [clojure.set :refer [subset?]]))

(defn parse-line [line]
  (let [[_ a b] (re-matches #"(.*?) \| (.*)" line)]
    {:patterns (map set (re-seq #"\w+" a))
     :output (map set (re-seq #"\w+" b))}))

(defn count-1478 [digits]
  (->> digits
       (map count)
       (filter #{2 3 4 7})
       count))

(defn decode [{patterns :patterns output :output}]
  (let [d1 (first (filter #(= (count %) 2) patterns))
        d4 (first (filter #(= (count %) 4) patterns))
        d7 (first (filter #(= (count %) 3) patterns))
        d8 (first (filter #(= (count %) 7) patterns))
        d069 (set (filter #(= (count %) 6) patterns))
        d235 (set (filter #(= (count %) 5) patterns))
        d9 (first (filter #(subset? d4 %) d069))
        d3 (first (filter #(subset? d1 %) d235))
        d0 (first (filter #(subset? d1 %) (disj d069 d9)))
        d6 (first (disj d069 d0 d9))
        d5 (first (filter #(subset? % d6) d235))
        d2 (first (disj d235 d3 d5))]
    (->> output
         (map #(condp = % d0 0 d1 1 d2 2 d3 3 d4 4 d5 5 d6 6 d7 7 d8 8 d9 9))
         join
         Integer/parseInt)))

(defn parse-input [input]
  (->> input
       split-lines
       (map parse-line)))

(defn a [input]
  (->> input
       parse-input
       (map :output)
       (map count-1478)
       (reduce +)))

(defn b [input]
  (->> input
       parse-input
       (map decode)
       (reduce +)))
