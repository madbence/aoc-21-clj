(ns madbence.aoc-21.day-13
  (:require [clojure.string :refer [split-lines]]))

(defn parse-input [input]
  (let [[coords [_ & folds]] (->> input
                                  split-lines
                                  (split-with seq))]
    {:coords (->> coords
                  (map #(re-matches #"(\d+),(\d+)" %))
                  (map #(vector (Integer/parseInt (nth % 1))
                                (Integer/parseInt (nth % 2))))
                  set)
     :folds (->> folds
                 (map #(re-matches #".*(x|y)=(\d+)" %))
                 (map #(hash-map :axis (case (nth % 1) "x" :x :y)
                                 :value (Integer/parseInt (nth % 2)))))}))

(defn fold [coords {axis :axis value :value}]
  (case axis
    :x (set (map (fn [[x y]] (if (> x value) [(- value (- x value)) y] [x y])) coords))
    :y (set (map (fn [[x y]] (if (> y value) [x (- value (- y value))] [x y])) coords))))

(defn print-coords [coords]
  (let [w (->> coords (map first) (reduce max) inc)
        h (->> coords (map second) (reduce max) inc)]
    (doseq [y (range h)]
      (doseq [x (range w)]
        (print (if (contains? coords [x y]) "█" " ")))
      (print "\n"))
    (print "\n")))

(defn a [input]
  (let [{coords :coords folds :folds} (parse-input input)]
    (count (fold coords (first folds)))))

(defn b [input]
  (let [{coords :coords folds :folds} (parse-input input)]
    (print-coords (reduce fold coords folds))))
