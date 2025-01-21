(ns chess.moves.queen-test
  (:require [clojure.test :refer :all]
            [chess.moves.queen :refer :all]))

(deftest invalid-queen-test
  (let [white-pawn {:type :pawn
                    :colour :white}
        white-queen {:type :queen
                     :colour :white}
        black-pawn {:type :pawn
                    :colour :black}]
    (testing "Valid queen file - on shared file"
      (let [move [:a2 :a4]
            position {:a2 white-queen}]
        (is (nil? (invalid-queen-move? move position :white)))))

    (testing "Valid queen move - on shared rank"
      (let [move [:a2 :c2]
            position {:a2 white-queen}]
        (is (nil? (invalid-queen-move? move position :white)))))

    (testing "Valid queen move - on shared diagonal"
      (let [move [:a2 :c4]
            position {:a2 white-queen}]
        (is (nil? (invalid-queen-move? move position :white)))))

    (testing "Invalid queen move - not on diagonal"
      (let [move [:a2 :b5]
            position {:a2 white-queen}]
        (is (not (nil? (invalid-queen-move? move position :white))))))

    (testing "Invalid queen move - piece blocking"
      (let [move [:a2 :c4]
            position {:a2 white-queen
                   :b3 black-pawn}]
        (is (not (nil? (invalid-queen-move? move position :white))))))

    (testing "Invalid queen move - would take own piece"
      (let [move [:a2 :c4]
            position {:a2 white-queen
                   :c4 white-pawn}]
        (is (not (nil? (invalid-queen-move? move position :white))))))

    (testing "Valid queen move - would take a piece"
      (let [move [:a2 :c4]
            position {:a2 white-queen
                   :c4 black-pawn}]
        (is (nil? (invalid-queen-move? move position :white))))))


  )
