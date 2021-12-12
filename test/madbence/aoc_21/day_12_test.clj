(ns madbence.aoc-21.day-12-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-12 :refer [a b]]))

(def fixture-1 "start-A
start-b
A-c
A-b
b-d
A-end
b-end")

(def fixture-2 "dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc")

(def fixture-3 "fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW")

(deftest test-a
  (is (= 10 (a fixture-1)))
  (is (= 19 (a fixture-2)))
  (is (= 226 (a fixture-3))))

(deftest test-b
  (is (= 36 (b fixture-1)))
  (is (= 103 (b fixture-2)))
  (is (= 3509 (b fixture-3))))
