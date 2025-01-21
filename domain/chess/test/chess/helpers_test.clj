(ns chess.helpers-test
  (:require [clojure.test :refer :all]
            [chess.helpers :refer :all]))

(deftest file-test
  (is (= 3 (file :c7)))
  (is (= 1 (file :a8)))
  (is (= 4 (file :d3))))

(deftest rank-test
  (is (= 1 (rank :a1)))
  (is (= 6 (rank :b6)))
  (is (= 7 (rank :c7))))

(deftest on-adjacent-files-test
  (is (on-adjacent-files? :d2 :e2))
  (is (on-adjacent-files? :d2 :e5))
  (is (not (on-adjacent-files? :d2 :f5)))
  (is (not (on-adjacent-files? :a2 :e5)))
  (is (not (on-adjacent-files? :d2 :d5))))

(deftest file-difference-test
  (is (=  1 (file-difference :d5 :e3)))
  (is (= -5 (file-difference :f3 :a4)))
  (is (=  2 (file-difference :c2 :e5))))

(deftest rank-difference-test
  (is (=  2 (rank-difference :e3 :d5 :white)))
  (is (= -1 (rank-difference :a4 :f3 :white)))
  (is (= -3 (rank-difference :d2 :e5 :black))))

(deftest add-rank-test
  (is (= :e4 (add-rank :e2 2 :white)))
  (is (= :e5 (add-rank :e7 2 :black))))

