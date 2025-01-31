(ns wizard.score.position-test
  (:require [clojure.test :refer :all]
            [wizard.score.position :refer :all]))

(deftest position-score-test
  (let [white-pawn {:type :pawn
                    :colour :white}
        black-pawn {:type :pawn
                    :colour :black}]

    (testing "Prefers to move central pawns"
      (is (> (position-score {:b2 {:type :pawn :colour :white}
                              :d4 {:type :pawn :colour :white}} :white)
             (position-score {:b4 {:type :pawn :colour :white}
                              :d2 {:type :pawn :colour :white}} :white))))

    (testing "Prefers to move pawn two squares"
      (is (> (position-score {:b2 {:type :pawn :colour :white}
                              :d4 {:type :pawn :colour :white}} :white)
             (position-score {:b2 {:type :pawn :colour :white}
                              :d3 {:type :pawn :colour :white}} :white))))))

