(ns madbence.aoc-21.day-14-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-14 :refer [parse-input evolve a b]]))

(def fixture
  "NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C")

(deftest test-evolve
  (let [[template rules] (parse-input fixture)]
    (is (= (-> template
               (evolve rules)
               :chars)
           (frequencies "NCNBCHB")))
    (is (= (-> template
               (evolve rules)
               (evolve rules)
               :chars)
           (frequencies "NBCCNBBBCBHCB")))
    (is (= (-> template
               (evolve rules)
               (evolve rules)
               (evolve rules)
               :chars)
           (frequencies "NBBBCNCCNBBNBNBBCHBHHBCHB")))
    (is (= (-> template
               (evolve rules)
               (evolve rules)
               (evolve rules)
               (evolve rules)
               :chars)
           (frequencies "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB")))))

(deftest test-a
  (is (= (a fixture) 1588)))

(deftest test-b
  (is (= (b fixture) 2188189693529)))
