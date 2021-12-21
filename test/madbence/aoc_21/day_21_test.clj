(ns madbence.aoc-21.day-21-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-21 :refer [a b]]))

(def fixture "Player 1 starting position: 4
Player 2 starting position: 8")

(deftest test-a
  (is (= (a fixture) 739785)))

(deftest tesb-b
  (is (= (b fixture) 444356092776315)))
