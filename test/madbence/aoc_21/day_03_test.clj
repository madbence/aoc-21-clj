(ns madbence.aoc-21.day-03-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-03 :refer [a b get-rate get-rating]]))

(def fixture
  "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")

(deftest test-gamma-rate
  (is (= (get-rate fixture <) 22)))

(deftest test-epsilon-rate
  (is (= (get-rate fixture >) 9)))

(deftest test-a
  (is (= (a fixture) 198)))

(deftest test-oxygen-rating
  (is (= (get-rating fixture <=) 23)))

(deftest test-co2-rating
  (is (= (get-rating fixture >) 10)))

(deftest test-b
  (is (= (b fixture) 230)))
