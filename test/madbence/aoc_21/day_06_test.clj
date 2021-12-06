(ns madbence.aoc-21.day-06-test
  (:require [madbence.aoc-21.day-06 :refer [a b simulate]]
            [clojure.test :refer [deftest is]]))

(deftest test-simulate
  (is (= (simulate "3,4,3,1,2" 0) 5))
  (is (= (simulate "3,4,3,1,2" 18) 26)))

(deftest test-a
  (is (= (a "3,4,3,1,2") 5934)))

(deftest test-b
  (is (= (b "3,4,3,1,2") 26984457539)))
