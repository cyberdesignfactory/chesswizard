(ns chess.moves.bishop-test
  (:require [clojure.test :refer :all]
            [chess.moves.bishop :refer :all]))

(deftest invalid-bishop-test
  (let [white-pawn   {:type :pawn
                      :colour :white}
        white-bishop {:type :bishop
                      :colour :white}
        black-pawn   {:type :pawn
                      :colour :black}]
    (testing "Valid bishop move"
      (let [move [:a2 :c4]
            position {:a2 white-bishop}]
        (is (nil? (invalid-bishop-move? move position :white)))))

    (testing "Invalid bishop move - not on diagonal"
      (let [move [:a2 :b5]
            position {:a2 white-bishop}]
        (is (not (nil? (invalid-bishop-move? move position :white))))))

    (testing "Invalid bishop move - piece blocking"
      (let [move [:a2 :c4]
            position {:a2 white-bishop
                   :b3 black-pawn}]
        (is (not (nil? (invalid-bishop-move? move position :white))))))

    (testing "Invalid bishop move - would take own piece"
      (let [move [:a2 :c4]
            position {:a2 white-bishop
                   :c4 white-pawn}]
        (is (not (nil? (invalid-bishop-move? move position :white))))))))

