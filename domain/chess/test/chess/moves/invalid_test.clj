(ns chess.moves.invalid-test
  (:require [clojure.test :refer :all]
            [chess.moves.invalid :refer :all]))

(deftest invalid-move-test
  (testing "Invalid king move - attempting to castle into check"
    (let [position {:a1 {:type :rook :colour :white :can-still-castle? true}
                    :c4 {:type :rook :colour :black}
                    :e1 {:type :king :colour :white :can-still-castle? true}
                    :g4 {:type :rook :colour :black}
                    :h1 {:type :rook :colour :white :can-still-castle? true}}]

          (is (invalid-move? [:e1 :c1] position :white))
          (is (invalid-move? [:e1 :g1] position :white)))))

