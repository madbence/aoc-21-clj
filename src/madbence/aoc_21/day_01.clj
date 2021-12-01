(ns madbence.aoc-21.day-01
  (:require [clojure.string :refer [split-lines]]))

(defn a [input]
  (->> input
       split-lines
       (map #(Integer/parseInt %))
       (partition 2 1)
       (filter #(apply < %))
       count))

(defn b [input]
  (->> input
       split-lines
       (map #(Integer/parseInt %))
       (partition 3 1)
       (map #(apply + %))
       (partition 2 1)
       (filter #(apply < %))
       count))
