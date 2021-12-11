(ns madbence.aoc-21.day-11
  (:require [madbence.aoc-21.day-09 :refer [parse-input]]))

(defn neighbours [[x y]]
  (for [x' [-1 0 1]
        y' [-1 0 1]
        :let [p [(+ x x') (+ y y')]]
        :when (not= p [x y])] p))

(defn evolve [grid]
  (loop [grid (into {} (for [[p v] grid] [p (inc v)]))
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
        (into {} (for [[p v] grid] (if (< 9 v) [p 0] [p v])))
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
