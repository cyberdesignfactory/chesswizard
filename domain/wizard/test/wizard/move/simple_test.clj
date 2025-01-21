(ns wizard.move.simple-test
  (:require [clojure.test :refer :all]
            [wizard.move.simple :refer :all]))

(deftest simple-move-test
  (let [
        white-pawn   {:type :pawn
                      :colour :white}
        white-knight {:type :knight
                      :colour :white}
        white-bishop {:type :bishop
                      :colour :white}
        white-rook   {:type :rook
                      :colour :white}
        white-queen  {:type :queen
                      :colour :white}
        white-king   {:type :king
                      :colour :white
                      :can-still-castle? true}
        black-pawn   {:type :pawn
                      :colour :black}
        black-knight {:type :knight
                      :colour :black}
        black-bishop {:type :bishop
                      :colour :black}
        black-rook   {:type :rook
                      :colour :black}
        black-queen  {:type :queen
                      :colour :black}
        black-king   {:type :king
                      :colour :black
                      :can-still-castle? true}]

    (testing "Only one possible move"
      (is (= [:e3 :e4]
             (simple-move {:e3 white-pawn}
                          :white))))
    (testing "Can take a piece"
      (is (= [:e3 :f4]
             (simple-move {:a2 white-pawn
                           :e3 white-pawn
                           :f4 black-pawn
                           :h3 white-pawn}
                          :white)))
      (is (= [:e6 :f5]
             (simple-move {:a7 black-pawn
                           :e6 black-pawn
                           :f5 white-pawn
                           :h6 black-pawn}
                          :black))))

    (testing "First black move"
      (let [position {
                   :a1 white-rook
                   :b1 white-knight
                   :c1 white-bishop
                   :d1 white-queen
                   :e1 white-king
                   :f1 white-bishop
                   :g1 white-knight
                   :h1 white-rook

                   :a2 white-pawn
                   :b2 white-pawn
                   :c2 white-pawn
                   :d2 white-pawn
                   :e2 white-pawn
                   :f2 white-pawn
                   :g2 white-pawn
                   :h2 white-pawn

                   :a7 black-pawn
                   :b7 black-pawn
                   :c7 black-pawn
                   :d7 black-pawn
                   :e7 black-pawn
                   :f7 black-pawn
                   :g7 black-pawn
                   :h7 black-pawn

                   :a8 black-rook
                   :b8 black-knight
                   :c8 black-bishop
                   :d8 black-queen
                   :e8 black-king
                   :f8 black-bishop
                   :g8 black-knight
                   :h8 black-rook

                   }]
        (is (not (nil? (simple-move position :black))))


        )


      ))


  )
