(ns madbence.aoc-21.day-05-test
  (:require [clojure.test :refer [is deftest]]
            [madbence.aoc-21.day-05 :refer [range' to-line a b]]))

(def fixture
  "0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2")

(deftest test-range
  (is (= (range' 0 0) [0]))
  (is (= (range' 0 1) [0 1]))
  (is (= (range' 1 0) [1 0])))

(deftest test-line
  (is (= (to-line 0 0 0 1) [[0 0] [0 1]]))
  (is (= (to-line 0 0 1 1) [[0 0] [1 1]]))
  (is (= (to-line 1 1 0 0) [[1 1] [0 0]]))
  (is (= (to-line 1 0 0 0) [[1 0] [0 0]])))

(deftest test-a
  (is (= (a fixture) 5)))

(deftest test-b
  (is (= (b fixture) 12)))
