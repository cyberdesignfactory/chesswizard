(ns chess.position.update-test
  (:require [clojure.test :refer :all]
            [chess.position.update :refer :all]))

(deftest update-position-test
  (let [
        white-pawn   {:type :pawn
                      :colour :white}
        white-bishop {:type :bishop
                      :colour :white}
        white-rook   {:type :rook
                      :colour :white}
        white-queen  {:type :queen
                      :colour :white}
        white-king   {:type :king
                      :colour :white}
        white-king   {:type :king
                      :colour :white}
        white-king-csc {:type :king
                        :colour :white
                        :can-still-castle? true}
        black-pawn   {:type :pawn
                      :colour :black}
        black-bishop {:type :bishop
                      :colour :black}
        black-rook   {:type :rook
                      :colour :black}
        black-queen  {:type :queen
                      :colour :black}
        black-king   {:type :king
                      :colour :black}
        black-king-csc {:type :king
                        :colour :black
                        :can-still-castle? true}
        ]

    (testing "Pawn moved"
      (let [position {:d2 white-pawn
                      :e2 white-pawn
                      :f2 white-pawn}
            move [:e2
                  :e4]]
        (is (= {:d2 white-pawn
                :e4 (-> white-pawn
                        (assoc :just-moved-two-spaces? true))
                :f2 white-pawn}
               (update-position position move)))))

    (testing "Pawn takes"
      (let [position {:d2 white-pawn
                      :e2 white-pawn
                      :f2 white-pawn
                      :f3 black-pawn}
            move [:e2
                  :f3]]
        (is (= {:d2 white-pawn
                :f2 white-pawn
                :f3 white-pawn}
               (update-position position move)))))

    (testing "Pawn takes - en passant"
      (let [position {:d2 white-pawn
                      :e2 white-pawn
                      :f4 (-> white-pawn
                              (assoc :just-moved? true))
                      :e4 black-pawn}
            move [:e4 :f3]]
        (is (= {:d2 white-pawn
                :e2 white-pawn
                :f3 black-pawn}
               (update-position position move)))))

    (testing "White king castles left"
      (let [position {:a1 white-rook
                      :e1 white-king-csc
                      :f1 white-bishop
                      :h1 white-rook
                      }
            move [:e1 :c1]]
        (is (= {:d1 white-rook
                :c1 white-king
                :f1 white-bishop
                :h1 white-rook}
               (update-position position move)))))

    (testing "White king castles right"
      (let [position {:a1 white-rook
                      :d1 white-queen
                      :e1 white-king-csc
                      :h1 white-rook}
            move [:e1
                  :g1]]
        (is (= {:a1 white-rook
                :d1 white-queen
                :f1 white-rook
                :g1 white-king}
               (update-position position move)))))

    (testing "Black king castles left"
      (let [position {:a8 black-rook
                      :e8 black-king-csc
                      :f8 black-bishop
                      :h8 black-rook
                      }
            move [:e8 :c8]]
        (is (= {:d8 black-rook
                :c8 black-king
                :f8 black-bishop
                :h8 black-rook}
               (update-position position move)))))

    (testing "Black king castles right"
      (let [position {:a8 black-rook
                      :d8 black-queen
                      :e8 black-king-csc
                      :h8 black-rook}
            move [:e8 :g8]]
        (is (= {:a8 black-rook
                :d8 black-queen
                :f8 black-rook
                :g8 black-king}
               (update-position position move)))))

    (testing "White pawn promoted"
      (let [position {:e7 {:type :pawn :colour :white}}
            move [:e7 :e8]]
        (is (= {:e8 {:type :queen :colour :white}}
               (update-position position move)))))

    (testing "Black pawn promoted"
      (let [position {:e2 {:type :pawn :colour :black}}
            move [:e2 :e1]]
        (is (= {:e1 {:type :queen :colour :black}}
               (update-position position move)))))

    ))

