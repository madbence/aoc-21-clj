(ns madbence.aoc-21.day-09
  (:require [clojure.string :refer [split-lines]]))

(defn parse-input [input]
  (->> input
       split-lines
       (map-indexed (fn [y line]
                      (map-indexed (fn [x c] [[x y] (Integer/parseInt (str c))]) line)))
       (reduce into {})))

(defn get-neighbours [[x y]]
  [[(dec x) y]
   [(inc x) y]
   [x (dec y)]
   [x (inc y)]])

(defn flood-fill [pos grid]
  (if (= (get grid pos) 9)
    nil
    (loop [basin #{pos}
           queue #{pos}]
      (if (empty? queue)
        basin
        (let [pos (first queue)
              neighbours (->> pos get-neighbours (filter #(not= (get grid % 9) 9)))]
          (recur (into basin neighbours)
                 (into (disj queue pos)
                       (filter #(not (basin %)) neighbours))))))))

(defn a [input]
  (let [grid (parse-input input)]
    (->> grid
         (filter (fn [[pos v]]
                   (->> pos
                        get-neighbours
                        (every? (fn [pos'] (< v (get grid pos' 9)))))))
         (map second)
         (map inc)
         (reduce +))))

(defn b [input]
  (let [grid (parse-input input)]
    (->> grid
         (map first)
         (keep #(flood-fill % grid))
         set
         (map count)
         sort
         reverse
         (take 3)
         (reduce *))))
