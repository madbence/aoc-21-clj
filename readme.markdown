```clojure
;; run
(require 'madbence.aoc-21.day-01)
(madbence.aoc-21.day-01/a (slurp "inputs/day_01.txt"))
(madbence.aoc-21.day-01/b (slurp "inputs/day_01.txt"))

;; test
(require 'madbence.aoc-21.day-01-test)
(clojure.test/run-tests 'madbence.aoc-21.day-01-test)
```
