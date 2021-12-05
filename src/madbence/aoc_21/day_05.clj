(ns madbence.aoc-21.day-05
  (:require [clojure.string :refer [split-lines]]))

(defn parse-int [s]
  (Integer/parseInt s))

(defn parse-input [s]
  (->> (split-lines s)
       (map #(re-matches #"(\d+),(\d+) -> (\d+),(\d+)" %))
       (map rest)
       (map #(map parse-int %))))

(defn range' [a b]
  (if (< b a)
    (range a (dec b) -1)
    (range a (inc b))))

(defn diagonal? [x1 y1 x2 y2]
  (and (not= x1 x2) (not= y1 y2)))

(defn to-line [x1 y1 x2 y2]
  (let [xs (range' x1 x2)
        ys (range' y1 y2)]
    (->> (map vector (cycle xs) (cycle ys))
         (take (max (count xs) (count ys))))))

(defn a [input]
  (->> input
       parse-input
       (filter #(not (apply diagonal? %)))
       (map #(apply to-line %))
       (apply concat)
       frequencies
       (map second)
       (filter #(< 1 %))
       count))

(defn b [input]
  (->> input
       parse-input
       (map #(apply to-line %))
       (apply concat)
       frequencies
       (map second)
       (filter #(< 1 %))
       count))
