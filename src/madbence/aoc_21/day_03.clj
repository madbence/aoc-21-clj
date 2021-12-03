(ns madbence.aoc-21.day-03
  (:require [clojure.string :refer [split-lines join]]))

(defn transpose [a]
  (map (fn [i] (map #(nth % i) a))
       (range (count (first a)))))

(defn parse-binary [s]
  (Integer/parseInt s 2))

(defn get-rate [input op]
  (->> input
       split-lines
       transpose
       (map frequencies)
       (map #(if (op (get % \0) (get % \1)) 1 0))
       join
       parse-binary))

(defn get-rating [xs op]
  (loop [xs (split-lines xs)
         pos 0]
    (if (= (count xs) 1)
      (parse-binary (first xs))
      (let [zeros (->> xs (filter #(= \0 (nth % pos))))
            ones  (->> xs (filter #(= \1 (nth % pos))))]
        (if (op (count zeros) (count ones))
          (recur ones  (inc pos))
          (recur zeros (inc pos)))))))

(defn a [input]
  (let [gamma-rate   (get-rate input <)
        epsilon-rate (get-rate input >)]
    (* gamma-rate epsilon-rate)))

(defn b [input]
  (let [oxygen-rating (get-rating input <=)
        co2-rating    (get-rating input >)]
    (* oxygen-rating co2-rating)))
