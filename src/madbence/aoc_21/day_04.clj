(ns madbence.aoc-21.day-04
  (:require [clojure.string :refer [split-lines]]))

(defn transpose [a]
  (map (fn [i] (map #(nth % i) a))
       (range (count (first a)))))

(defn parse-line [s]
  (map #(Integer/parseInt %) (re-seq #"\d+" s)))

(defn make-board [lines]
  (concat (map set lines)
          (map set (transpose lines))))

(defn parse-boards [lines]
  (->> lines
       (partition-by empty?)
       (filter #(seq (first %)))
       (map make-board)))

(defn mark-board [board n]
  (map #(disj % n) board))

(defn mark-boards [boards n]
  (map #(mark-board % n) boards))

(defn bingo? [board]
  (some empty? board))

(defn get-score [n board]
  (->> board
       (map #(reduce + %))
       (reduce +)
       (* n 0.5)
       int))

(defn play [input stop-pred]
  (let [lines (->> input split-lines (map parse-line))]
    (loop [numbers (first lines)
           boards (parse-boards (rest lines))]
      (let [n (first numbers)
            boards' (mark-boards boards n)]
        (if (stop-pred bingo? boards')
          (->> boards'
               (filter bingo?)
               first
               (get-score n))
          (recur (rest numbers)
                 (filter #(not (bingo? %)) boards')))))))

(defn a [input]
  (play input some))

(defn b [input]
  (play input every?))
