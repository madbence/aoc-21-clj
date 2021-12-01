(ns madbence.aoc-21.day-01
  (:require [clojure.string :refer [split-lines]]))

(defn parse-int [s]
  (Integer/parseInt s))

(defn a [input]
  (->> input
       split-lines
       (map parse-int)
       (partition 2 1)
       (map #(apply < %))
       (filter true?)
       count))

(defn b [input]
  (->> input
       split-lines
       (map parse-int)
       (partition 3 1)
       (map #(apply + %))
       (partition 2 1)
       (map #(apply < %))
       (filter true?)
       count))
