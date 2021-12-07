(ns madbence.aoc-21.day-07-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-07 :refer [a b]]))

(def fixture "16,1,2,0,4,2,7,1,2,14")

(deftest test-a
  (is (= (a fixture) 37)))

(deftest test-b
  (is (= (b fixture) 168)))
