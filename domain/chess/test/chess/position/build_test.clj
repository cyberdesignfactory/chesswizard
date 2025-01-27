(ns chess.position.build-test
  (:require [clojure.test :refer :all]
            [chess.position.build :refer :all]))

(deftest build-position-test

  (testing "Kings can no longer castle if they have been in check`"
    (let [position {:e1 {:type :king :colour :white :can-still-castle? true}
                    :d4 {:type :rook :colour :black}}
          move [:d4 :e4]]
      (is (not (get-in (build-position position [move])
                       [:e1 :can-still-castle?]))))))

