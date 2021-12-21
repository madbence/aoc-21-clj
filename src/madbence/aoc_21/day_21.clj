(ns madbence.aoc-21.day-21
  (:require [clojure.string :refer [split-lines]]))

;; part 1

(defn parse-input [input]
  (let [[[_ a] [_ b]] (->> input split-lines (map #(re-matches #".*: (\d)$" %)))]
    [(Integer/parseInt a) (Integer/parseInt b) 0 0 0 0]))

(defn add-pos [old n]
  (inc (rem (+ (dec old) n) 10)))

(defn step-player [state n]
  (update state (nth state 4) add-pos n))

(defn update-score [state]
  (update state (+ (nth state 4) 2) + (nth state (nth state 4))))

(defn step [state]
  (let [sum (->> (nth state 5) (iterate inc) (take 3) (map #(inc (rem % 100))) (reduce +))]
    (-> state
        (step-player sum)
        update-score
        (update 4 #(case % 0 1 1 0))
        (update 5 + 3))))

(defn score [[_ _ a b _ s]]
  (* s (min a b)))

(defn a [input]
  (->> input
       parse-input
       (iterate step)
       (drop-while #(and (< (nth % 2) 1000)
                         (< (nth % 3) 1000)))
       first
       score))

;; part 2

(def splits [1 3 6 7 6 3 1])

(defn next-state [state n]
  (-> state (step-player n) update-score (update 4 #(case % 0 1 1 0))))

(defn count-outcomes
  ([state]
   (get (count-outcomes state {}) state))
  ([state cache]
   (cond
     (contains? cache state) cache
     (>= (nth state 2) 21) (assoc cache state [1 0])
     (>= (nth state 3) 21) (assoc cache state [0 1])
     :else (let [states (->> (range 3 10) (map #(next-state state %)))
                 cache' (->> states (reduce #(count-outcomes %2 %1) cache))]
             (assoc cache' state (->> states
                                      (map #(get cache' %))
                                      (map #(map * (repeat %1) %2) splits)
                                      (reduce #(map + %1 %2))))))))

(defn b [input]
  (->> input
       parse-input
       count-outcomes
       (reduce max)))
