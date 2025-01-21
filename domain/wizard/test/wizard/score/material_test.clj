(ns wizard.score.material-test
  (:require [clojure.test :refer :all]
            [wizard.score.material :refer :all]))

(deftest position-score-test
  (let [
        white-pawn {:type :pawn
                    :colour :white}
        black-pawn {:type :pawn
                    :colour :black}]

    (testing "Two pawns vs one pawn"
      (let [position {:a2 white-pawn
                   :b2 white-pawn
                   :h7 black-pawn}]

        (is (=  1 (position-score position :white)))
        (is (= -1 (position-score position :black)))))))

