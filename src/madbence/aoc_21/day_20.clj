(ns madbence.aoc-21.day-20
  (:require [clojure.string :refer [split-lines join]]))

(defn parse-input [input]
  (let [[rules _ & lines] (->> input split-lines (map #(map (fn [c] (case c \. 0 1)) %)))]
    [rules (->> lines
              (map-indexed (fn [y line] (map-indexed (fn [x c] [[x y] c]) line)))
              (reduce into {}))]))

(defn get-rectangle [[x1 y1] [x2 y2]]
  (for [y (range y1 (inc y2))
        x (range x1 (inc x2))] [x y]))

(defn get-hash [[x y] grid default]
  (Integer/parseInt (join (map #(get grid % default) (get-rectangle [(dec x) (dec y)] [(inc x) (inc y)]))) 2))

(defn step [rules grid default]
  (into {} (map #(vector % (nth rules (get-hash % grid default))) (get-rectangle [(->> grid (map first) (map first)  (reduce min) dec)
                                                                                  (->> grid (map first) (map second) (reduce min) dec)]
                                                                                 [(->> grid (map first) (map first)  (reduce max) inc)
                                                                                  (->> grid (map first) (map second) (reduce max) inc)]))))

(defn evolve [rules limit grid]
  (->> [grid 0]
       (iterate #(vector (step rules (first %) (second %))
                         (if (zero? (second %))
                           (nth rules 0)
                           (nth rules 511))))
       (drop limit)
       first
       first
       (map second)
       (filter pos?)
       count))

(defn a [input]
  (let [[rules grid] (parse-input input)]
    (evolve rules 2 grid)))

(defn b [input]
  (let [[rules grid] (parse-input input)]
    (evolve rules 50 grid)))
