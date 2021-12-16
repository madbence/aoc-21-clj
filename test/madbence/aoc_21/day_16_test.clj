(ns madbence.aoc-21.day-16-test
  (:require [clojure.test :refer [deftest is]]
            [madbence.aoc-21.day-16 :refer [decode-packet a b]]))

(deftest test-decode-packet
  (is (= (decode-packet "D2FE28") {:version 6 :type 4 :value 2021 :length 21}))
  (is (= (decode-packet "38006F45291200") {:version 1
                                           :type 6
                                           :length 49
                                           :packets [{:type 4 :version 6 :value 10 :length 11}
                                                     {:type 4 :version 2 :value 20 :length 16}]}))
  (is (= (decode-packet "EE00D40C823060") {:version 7
                                           :type 3
                                           :length 51
                                           :packets [{:type 4 :version 2 :value 1 :length 11}
                                                     {:type 4 :version 4 :value 2 :length 11}
                                                     {:type 4 :version 1 :value 3 :length 11}]})))

(deftest test-a
  (is (= (a "8A004A801A8002F478") 16))
  (is (= (a "620080001611562C8802118E34") 12))
  (is (= (a "C0015000016115A2E0802F182340") 23))
  (is (= (a "A0016C880162017C3686B18A3D4780") 31)))

(deftest test-b
  (is (= (b "C200B40A82") 3))
  (is (= (b "04005AC33890") 54))
  (is (= (b "880086C3E88112") 7))
  (is (= (b "CE00C43D881120") 9))
  (is (= (b "D8005AC2A8F0") 1))
  (is (= (b "F600BC2D8F") 0))
  (is (= (b "9C005AC2F8F0") 0))
  (is (= (b "9C0141080250320F1802104A08") 1)))
