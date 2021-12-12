(ns madbence.aoc-21.day-12
  (:require [clojure.string :refer [split-lines upper-case]]
            [clojure.set :refer [difference]]))

(defn add-edge [g [from to]]
  (-> g
      (update from (fnil conj #{}) to)
      (update to (fnil conj #{}) from)))

(defn parse-input [input]
  (->> input
       split-lines
       (map #(re-matches #"(\w+)-(\w+)" %))
       (map rest)
       (reduce add-edge {})))

(defn small? [node]
  (not= node (upper-case node)))

(defn paths-from [g visited from to can-visit-twice]
  (if (= from to)
    #{[to]}
    (->> (-> g (get from) (difference visited))
         (map (fn [node]
                (if (and can-visit-twice (small? node))
                  (into (paths-from g (conj visited node) node to true)
                        (paths-from g visited node to false))
                  (paths-from g (if (small? node) (conj visited node) visited) node to can-visit-twice))))
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
