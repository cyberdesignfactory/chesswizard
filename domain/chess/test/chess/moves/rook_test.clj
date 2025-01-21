(ns chess.moves.rook-test
  (:require [clojure.test :refer :all]
            [chess.moves.rook :refer :all]))

(deftest invalid-rook-test
  (let [white-pawn {:type :pawn
                    :colour :white}
        white-rook {:type :rook
                    :colour :white}
        black-pawn {:type :pawn
                    :colour :black}]
    (testing "Valid rook move - on shared file"
      (let [move [:a2 :a4]
            position {:a2 white-rook}]
        (is (nil? (invalid-rook-move? move position :white)))))

    (testing "Valid rook move - on shared rank"
      (let [move [:a2 :c2]
            position {:a2 white-rook}]
        (is (nil? (invalid-rook-move? move position :white)))))

    (testing "Invalid rook move - not on a shared file or rank"
      (let [move [:a2 :b5]
            position {:a2 white-rook}]
        (is (not (nil? (invalid-rook-move? move position :white))))))

    (testing "Invalid rook move - piece blocking"
      (let [move [:a2 :a4]
            position {:a2 white-rook
                   :a3 black-pawn}]
        (is (not (nil? (invalid-rook-move? move position :white))))))

    (testing "Invalid rook move - would take own piece"
      (let [move [:a2 :a4]
            position {:a2 white-rook
                   :a4 white-pawn}]
        (is (not (nil? (invalid-rook-move? move position :white))))))))

