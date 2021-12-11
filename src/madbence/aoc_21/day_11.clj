(ns madbence.aoc-21.day-11
  (:require [madbence.aoc-21.day-09 :refer [parse-input]]))

(defn neighbours [[x y]]
  (for [x' (range -1 2)
        y' (range -1 2)
        :when (not (and (= x' 0) (= y' 0)))] [(+ x x') (+ y y')]))

(defn map' [f m]
  (into {} (map #(assoc % 1 (f (second %)))) m))

(defn evolve [grid]
  (loop [grid (map' inc grid)
         flashed #{}]
    (let [just-flashed (->> grid
                            (filter #(< 9 (second %)))
                            (map first)
                            (filter #(not (flashed %))))
          affected (->> just-flashed
                        (mapcat neighbours)
                        (filter #(contains? grid %)))
          grid' (reduce #(update %1 %2 inc) grid affected)]
      (if (= grid grid')
        (map' #(if (< 9 %) 0 %) grid)
        (recur grid' (into flashed just-flashed))))))

(defn a [input]
  (->> input
       parse-input
       (iterate evolve)
       (take 101)
       (map #(->> % (map second) (filter zero?) count))
       (reduce +)))

(defn b [input]
  (->> input
       parse-input
       (iterate evolve)
       (take-while #(->> % (map second) (every? zero?) not))
       count))
