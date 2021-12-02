(ns madbence.aoc-21.day-02
  (:require [clojure.string :refer [split-lines]]))

(defn parse-instruction [s]
  (let [[_ command amount] (re-matches #"(\w+) (\d+)" s)]
    [command (Integer/parseInt amount)]))

(defn move [pos [command amount]]
  (case command
    "forward" (update pos 0 + amount)
    "up" (update pos 1 - amount)
    "down" (update pos 1 + amount)))

(defn move' [pos [command amount]]
  (case command
    "forward" (-> pos (update 0 + amount) (update 1 + (* amount (nth pos 2))))
    "up" (update pos 2 - amount)
    "down" (update pos 2 + amount)))

(defn a [input]
  (->> input
       split-lines
       (map parse-instruction)
       (reduce move [0 0])
       (apply *)))

(defn b [input]
  (->> input
       split-lines
       (map parse-instruction)
       (reduce move' [0 0 0])
       drop-last
       (apply *)))
