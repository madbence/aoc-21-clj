(ns madbence.aoc-21.day-12
  (:require [clojure.string :refer [split-lines upper-case]]
            [clojure.set :refer [difference]]))

(defn parse-input [input]
  (->> input
       split-lines
       (map #(re-matches #"(\w+)-(\w+)" %))
       (map rest)
       (mapcat #(vector % (reverse %)))
       (reduce #(update %1 (first %2) (fnil conj #{}) (second %2)) {})))

(defn paths-from [g visited from to can-visit-twice]
  (if (= from to)
    #{[to]}
    (->> (-> g (get from) (difference visited))
         (map #(cond
                 (= % (upper-case %)) (paths-from g visited % to can-visit-twice)
                 (not can-visit-twice) (paths-from g (conj visited %) % to false)
                 :else (into (paths-from g (conj visited %) % to true)
                             (paths-from g visited % to false))))
         (reduce into #{})
         (map #(conj % from))
         set)))

(defn a [input]
  (-> input
      parse-input
      (paths-from #{"start"} "start" "end" false)
      count))

(defn b [input]
  (-> input
      parse-input
      (paths-from #{"start"} "start" "end" true)
      count))
