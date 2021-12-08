(ns madbence.aoc-21.day-08-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-08 :refer [decode parse-line a b]]
            [clojure.string :refer [split-lines]]))

(def fixture
  "be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce")

(deftest test-a
  (is (= (a fixture) 26)))

(deftest test-decode
  (let [lines (split-lines fixture)]
    (is (= (-> lines (nth 0) parse-line decode) 8394))
    (is (= (-> lines (nth 1) parse-line decode) 9781))
    (is (= (-> lines (nth 2) parse-line decode) 1197))
    (is (= (-> lines (nth 3) parse-line decode) 9361))
    (is (= (-> lines (nth 4) parse-line decode) 4873))
    (is (= (-> lines (nth 5) parse-line decode) 8418))
    (is (= (-> lines (nth 6) parse-line decode) 4548))
    (is (= (-> lines (nth 7) parse-line decode) 1625))
    (is (= (-> lines (nth 8) parse-line decode) 8717))
    (is (= (-> lines (nth 9) parse-line decode) 4315))))

(deftest test-b
  (is (= (b fixture) 61229)))
