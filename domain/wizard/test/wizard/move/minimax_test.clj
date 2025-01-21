(ns wizard.move.minimax-test
  (:require [clojure.test :refer :all]
            [wizard.move.minimax :refer :all]))

(deftest minimax-move-test
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

    (testing "Zero moves ahead"
      (is (= [:e3 :e4]
             (minimax-move {:e3 white-pawn} :white 0))))

    (testing "Needs to think one move ahead - to not sacrifice queen to take a pawn"
      (is (not= [:e4 :e5]
                (minimax-move {:e4 white-queen
                               :e5 black-pawn
                               :f6 black-pawn} :white 1))))

    (testing "But here it can take the pawn"
      (is (= [:e4 :e5]
             (minimax-move {:e4 white-queen
                            :e5 black-pawn} :white 1))))

    (testing "Move must not lead to king being in check"
      (is (not= [:e1 :e2]
                (minimax-move {:e1 white-king
                               :e2 black-pawn
                               :e3 black-rook} :white 1)))

      (is (not= [:e1 :f2]
                (minimax-move {:e1 white-king
                               :c5 black-bishop
                               :f2 black-pawn} :white 1)))

      (is (not= [:g3 :h4]
                (minimax-move {:g3 white-pawn
                               :f2 white-king
                               :c5 black-bishop
                               :h4 black-pawn} :white 1)))

      ;; Must move out of check (by taking)
      (is (= [:e8 :f7]
             (minimax-move {
                            :e8 {:type :king :colour :black}
                            :f7 {:type :rook :colour :white}
                            :e7 {:type :pawn :colour :white}
                           } :black 1)))
      )

    (testing "But here it can take"
      (is (= [:e1 :e2]
             (minimax-move {:e1 white-king
                            :e2 black-pawn} :white 1)))))
  )

