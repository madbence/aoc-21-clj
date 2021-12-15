(ns madbence.aoc-21.day-15
  (:require [clojure.data.priority-map :as pm]
            [madbence.aoc-21.day-09 :refer [parse-input]]))

(defn get-neighbours [[x y]]
  [[(dec x) y]
   [(inc x) y]
   [x (dec y)]
   [x (inc y)]])

(defn shortest-path [grid from to]
  (loop [distances (pm/priority-map from 0)
         visited #{}]
    (let [curr (->> distances peek first)
          c (get distances curr)]
      (if (= curr to)
        (get distances curr)
        (let [neighbours (->> curr get-neighbours (filter #(contains? grid %)) (filter #(not (visited %))))
              distances' (reduce (fn [distances neighbour]
                                   (assoc distances neighbour (min (get distances neighbour ##Inf)
                                                                   (+ c (get grid neighbour)))))
                                 (pop distances) neighbours)]
          (recur distances'
                 (conj visited curr)))))))

(defn increase-value [v amount]
  (inc (rem (+ (dec v) amount) 9)))

(defn increase-cell [grid [[x y] v] amount ox oy]
  (assoc grid [(+ ox x) (+ oy y)] (increase-value v amount)))

(defn increase-grid [grid amount ox oy]
  (reduce #(increase-cell %1 %2 amount ox oy) {} grid))

(defn extend-grid [grid w h]
  (->> (for [x (range 5)
             y (range 5)] [x y (+ x y)])
       (reduce #(merge %1 (increase-grid grid (nth %2 2) (* w (nth %2 0)) (* h(nth %2 1)))) grid)))

(defn a [input]
  (let [grid (parse-input input)
        mx (->> grid keys (map first) (reduce max))
        my (->> grid keys (map second) (reduce max))]
    (shortest-path grid [0 0] [mx my])))

(defn b [input]
  (let [grid (parse-input input)
        mx (->> grid keys (map first) (reduce max))
        my (->> grid keys (map second) (reduce max))]
    (shortest-path (extend-grid grid (inc mx) (inc my)) [0 0] [(dec (* 5 (inc mx))) (dec (* 5 (inc my)))])))
