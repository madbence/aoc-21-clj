(ns madbence.aoc-21.day-09-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-09 :refer [a b]]))

(def fixture
  "2199943210
3987894921
9856789892
8767896789
9899965678")

(deftest test-a
  (is (= (a fixture) 15)))

(deftest test-b
  (is (= (b fixture) 1134)))
