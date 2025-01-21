(ns chess.notation.algebraic-test
  (:require [clojure.test :refer :all]
            [chess.notation.algebraic :refer :all]))

(deftest algebraic-move-notation-test
  (testing "Pawn moves"
    (let [half-move [:e2 :e4]
          initial-position {:e2 {:type :pawn
                                 :colour :white}}]
      (is (= "e4" (algebraic-move-notation half-move initial-position)))))

  (testing "Pawn takes"
    (let [half-move [:e2 :d3]
          initial-position {:e2 {:type :pawn
                                 :colour :white}
                            :d3 {:type :pawn
                                 :colour :black}}]
      (is (= "exd3" (algebraic-move-notation half-move initial-position)))))

  (testing "Knight moves"
    (let [half-move [:b2 :c4]
          initial-position {:b2 {:type :knight
                                 :colour :white}}]
      (is (= "Nc4" (algebraic-move-notation half-move initial-position)))))

  (testing "Knight takes"
    (let [half-move [:b2 :c4]
          initial-position {:b2 {:type :knight
                                 :colour :white}
                            :c4 {:type :queen
                                 :colour :black}}]
      (is (= "Nxc4" (algebraic-move-notation half-move initial-position))))))

