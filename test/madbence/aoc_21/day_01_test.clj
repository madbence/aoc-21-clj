(ns madbence.aoc-21.day-01-test
  (:require [clojure.test :refer [is deftest]]
            [madbence.aoc-21.day-01 :refer [a b]]))

(def fixture
  "199
200
208
210
200
207
240
269
260
263")

(deftest test-a
  (is (= (a fixture) 7)))

(deftest test-b
  (is (= (b fixture) 5)))
