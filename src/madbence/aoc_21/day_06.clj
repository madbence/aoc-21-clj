(ns madbence.aoc-21.day-06)

(def empty-pool
  (into {} (map vector (range 0 9) (repeat 0))))

(defn collect [pool]
  (reduce #(merge-with + %1 %2) empty-pool pool))

(defn parse-input [s]
  (->> s (re-seq #"\d+") (map #(hash-map (Integer/parseInt %) 1)) collect))

(defn evolve-fish [age n]
  (if (zero? age)
    {8 n 6 n}
    {(dec age) n}))

(defn evolve-pool [pool]
  (->> pool (map #(apply evolve-fish %)) collect))

(defn simulate [input days]
  (->> input
       parse-input
       (iterate evolve-pool)
       (drop days)
       first
       (map second)
       (reduce +)))

(defn a [input]
  (simulate input 80))

(defn b [input]
  (simulate input 256))
