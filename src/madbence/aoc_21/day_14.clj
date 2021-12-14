(ns madbence.aoc-21.day-14
  (:require [clojure.string :refer [split-lines join]]))

(defn parse-input [input]
  (let [[template _ & rules] (split-lines input)
        rules (->> rules
                   (map (fn [line]
                          (let [[_ [a b] c] (re-matches #"(..) -> (.)" line)]
                            [(str a b) [(str a c) (str c b)]])))
                   (into {}))]
    [{:pairs (->> template (partition 2 1) (map join) frequencies)
      :chars (frequencies template)}
     rules]))


(defn evolve [{pairs :pairs chars :chars} rules]
  {:chars (->> pairs
               (map (fn [[pair n]]
                      [(-> rules (get pair) first second) n]))
               (reduce #(update %1 (first %2) (fnil + 0) (second %2)) chars))
   :pairs (->> pairs
               (mapcat (fn [[pair n]]
                         [[(-> rules (get pair) first)  n]
                          [(-> rules (get pair) second) n]]))
               (reduce #(update %1 (first %2) (fnil + 0) (second %2)) {}))})

(defn get-score [{chars :chars}]
  (- (->> chars (map second) (reduce max))
     (->> chars (map second) (reduce min))))

(defn solve [input steps]
  (let [[template rules] (parse-input input)]
    (->> template
         (iterate #(evolve % rules))
         (drop steps)
         first
         get-score)))

(defn a [input]
  (solve input 10))

(defn b [input]
  (solve input 40))
