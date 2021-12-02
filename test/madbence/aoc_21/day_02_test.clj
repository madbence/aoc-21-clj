(ns madbence.aoc-21.day-02-test
  (:require [clojure.test :refer [is deftest]]
            [madbence.aoc-21.day-02 :refer [a b]]))

(def fixture
  "forward 5
down 5
forward 8
up 3
down 8
forward 2")

(deftest test-a
  (is (= (a fixture) 150))
  (is (= (b fixture) 900)))
