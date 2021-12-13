(ns madbence.aoc-21.day-13-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-13 :refer [a]]))

(def fixture
  "6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5")

(deftest test-a
  (is (= (a fixture) 17)))
