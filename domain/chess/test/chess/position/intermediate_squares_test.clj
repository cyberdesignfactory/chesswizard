(ns chess.position.intermediate-squares-test
  (:require [clojure.test :refer :all]
            [chess.position.intermediate-squares :refer :all]))

(deftest intermediate-squares-test
  (testing "On same file"
    (is (= [:e3]
           (intermediate-squares [:e2 :e4])))
    (is (= [:d4 :d3 :d2]
           (intermediate-squares [:d5 :d1]))))

  (testing "On same rank"
    (is (= [:e3]
           (intermediate-squares [:d3 :f3])))
    (is (= [:e3 :d3 :c3]
           (intermediate-squares [:f3 :b3]))))

  (testing "On same diagonal"
    (is (= [:f5]
           (intermediate-squares [:e4 :g6])))

    (is (= [:e3 :d4 :c5]
           (intermediate-squares [:f2 :b6])))

    (is (= [:e5 :d4 :c3]
           (intermediate-squares [:f6 :b2])))))


