(ns madbence.aoc-21.day-11-test
  (:require [clojure.test :refer [deftest is]]
            [clojure.string :refer [join]]
            [madbence.aoc-21.day-09 :refer [parse-input]]
            [madbence.aoc-21.day-11 :refer [evolve a b]]))

(defn format-grid [grid w h]
  (join "\n" (map (fn [y] (join (map #(get grid [% y]) (range w)))) (range h))))

(def fixture-1 "11111
19991
19191
19991
11111")

(def expected-1 "34543
40004
50005
40004
34543")

(def fixture-2 "5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526")

(deftest test-evolve
  (is (= expected-1 (format-grid (evolve (parse-input fixture-1)) 5 5))))

(deftest test-a
  (is (= (a fixture-2) 1656)))

(deftest test-b
  (is (= (b fixture-2) 195)))
