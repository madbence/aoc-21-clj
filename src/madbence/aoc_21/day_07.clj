(ns madbence.aoc-21.day-07)

(defn parse-input [input]
  (->> input (re-seq #"\d+") (map #(Integer/parseInt %))))

(defn get-fuel [crabs p cost-fn]
  (->> crabs
       (map #(cost-fn % p))
       (reduce +)))

(defn get-min-fuel [input cost-fn]
  (let [crabs (parse-input input)
        from (reduce min crabs)
        to (reduce max crabs)]
    (->> (range from (inc to))
         (map #(get-fuel crabs % cost-fn))
         (reduce min))))

(defn a [input]
  (get-min-fuel input #(Math/abs (- %1 %2))))

(defn b [input]
  (get-min-fuel input #(reduce + (range (inc (Math/abs (- %1 %2)))))))
