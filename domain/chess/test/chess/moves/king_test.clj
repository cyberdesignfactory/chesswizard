(ns chess.moves.king-test
  (:require [clojure.test :refer :all]
            [chess.moves.king :refer :all]))

(deftest invalid-king-test
  (let [white-pawn {:type :pawn
                    :colour :white}
        white-bishop {:type :bishop
                      :colour :white}
        white-rook {:type :rook
                    :colour :white}
        white-queen {:type :queen
                     :colour :white}
        white-king {:type :king
                    :colour :white}
        white-king-csc {:type :king
                        :colour :white
                        :can-still-castle? true}
        black-pawn {:type :pawn
                    :colour :black}
        black-rook {:type :rook
                    :colour :black}
        black-king {:type :king
                    :colour :black}
        black-king-csc {:type :king
                        :colour :black
                        :can-still-castle? true}]
    (testing "Valid king move - one file or rank moved"
      (let [position {:e4 white-king}]
        (is (not (invalid-king-move? [:e4 :d5] position :white)))
        (is (nil? (invalid-king-move? [:e4 :e5] position :white)))
        (is (nil? (invalid-king-move? [:e4 :f5] position :white)))
        (is (nil? (invalid-king-move? [:e4 :d4] position :white)))
        (is (nil? (invalid-king-move? [:e4 :f4] position :white)))
        (is (nil? (invalid-king-move? [:e4 :d3] position :white)))
        (is (nil? (invalid-king-move? [:e4 :e3] position :white)))
        (is (nil? (invalid-king-move? [:e4 :f3] position :white)))))

    (testing "Invalid king move - two files or ranks moved"
      (let [position {:e4 white-king}]
        (is (invalid-king-move? [:e4 :c6] position :white))
        (is (invalid-king-move? [:e4 :e6] position :white))
        (is (invalid-king-move? [:e4 :g6] position :white))
        (is (invalid-king-move? [:e4 :c4] position :white))
        (is (invalid-king-move? [:e4 :g4] position :white))
        (is (invalid-king-move? [:e4 :c2] position :white))
        (is (invalid-king-move? [:e4 :e2] position :white))
        (is (invalid-king-move? [:e4 :g2] position :white))))

    (testing "Invalid king move - not on diagonal"
      (let [move [:a2 :b5]
            position {:a2 white-king}]
        (is (invalid-king-move? move position :white))))

    (testing "Invalid king move - would take own piece"
      (let [move [:a2 :b3]
            position {:a2 white-king
                      :b3 white-pawn}]
        (is (invalid-king-move? move position :white))))

    (testing "Valid king move - would take opponent's piece"
      (let [move [:e8 :f7]
            position {:e8 black-king
                      :f7 white-bishop}]
        (is (not (invalid-king-move? move position :black)))))

    #_(testing "Invalid king move - would move into check"
        (let [move [:a2 :a3]
              board {:b4 [:pawn :black]}]
          (is (invalid-king-move? move board :piece.colour/white))))

    (testing "Valid king move - white castling"
      (let [position {:e1 white-king-csc
                      :a1 white-rook
                      :h1 white-rook}]
        (is (not (invalid-king-move? [:e1 :c1] position :white)))
        (is (not (invalid-king-move? [:e1 :g1] position :white)))))

    (testing "Valid king move - black castling"
      (let [position {:e8 black-king-csc
                      :a8 black-rook
                      :h8 black-rook}]
        (is (not (invalid-king-move? [:e8 :c8] position :black)))
        (is (not (invalid-king-move? [:e8 :g8] position :black)))))

    (testing "Invalid king move - white castling but piece in the way"
      (let [position {:a1 white-rook
                      :d1 white-queen
                      :e1 white-king-csc
                      :f1 white-bishop
                      :h1 white-rook}]
        (is (invalid-king-move? [:e1 :c1] position :white))
        (is (invalid-king-move? [:e1 :g1] position :white))))))

