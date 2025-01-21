(ns viewer.handlers-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [viewer.handlers :refer [square-selected-handler]]))

(deftest square-selected-handler-test
  (let [initial-game
        {:initial-position {
                            :e2 {:type :pawn :colour :white}
                            }
         :half-moves []
         :checkmate nil
         :stalemate? false
         :from-square nil
         :to-square nil}]

   (testing "from square selected"
      (let [square :e2

            ;; game (square-selected-handler initial-game square)

            game (square-selected-handler {:game initial-game
                                           :square square
                                           :on-valid-move (fn []   )
                                           :on-valid-move-callback (fn []   )
                                           }
                                          )
            ]
        (is (= [] (:half-moves game)))
        (is (= :e2 (:from-square game)))
        (is (nil? (:to-square game)))))

    (testing "from square unselected"
      (let [square :e2
            test-game (-> initial-game
                          (assoc :from-square :e2))
            game (square-selected-handler {:game test-game
                                           :square square
                                           :on-valid-move (fn []   )
                                           :on-valid-move-callback (fn []   )
                                           })]
        (is (= [] (:half-moves game)))
        (is (nil? (:from-square game)))
        (is (nil? (:to-square game)))))

    (testing "to square selected - invalid move"
      (with-redefs
        [chess.moves.invalid/invalid-move? (fn [position move colour-to-move] :invalid)]
        (let [square :e4
              test-game (-> initial-game
                            (assoc :from-square :e2))
              game (square-selected-handler {:game test-game
                                             :square square
                                             :on-valid-move (fn []   )
                                             :on-valid-move-callback (fn []   )
                                             })]
          (is (= [] (:half-moves game)))
          (is (= :e2 (:from-square game)))
          (is (nil? (:to-square game))))))

    (testing "to square selected - valid move"
      (with-redefs
        [chess.moves.invalid/invalid-move? (fn [position move colour-to-move] nil)]
        (let [square :e4
              test-game (-> initial-game
                            (assoc :from-square :e2))
              game (square-selected-handler {:game test-game
                                             :square square
                                             :on-valid-move (fn []   )
                                             :on-valid-move-callback (fn []   )
                                             })]
          (is (= [[:e2 :e4]] (:half-moves game)))
          (is (nil? (:from-square game)))
          (is (= :e4 (:to-square game))))))

    (testing "to square selected - checkmate"
      (with-redefs
        [chess.moves.invalid/invalid-move? (fn [position move colour-to-move] nil)
         chess.position.mate/is-checkmate? (fn [_ _] true)]
        (let [square :e4
              test-game (-> initial-game
                            (assoc :from-square :e2))
              game (square-selected-handler {:game test-game
                                             :square square
                                             :on-valid-move (fn []   )
                                             :on-valid-move-callback (fn []   )
                                             })]
          (is (= [[:e2 :e4]] (:half-moves game)))
          (is (nil? (:from-square game)))
          (is (= :e4 (:to-square game)))
          (is (= :white (:checkmate game)))
          )
        ))

    (testing "to square selected - stalemate"
      (with-redefs
        [chess.moves.invalid/invalid-move? (fn [position move colour-to-move] nil)
         chess.position.mate/is-stalemate? (fn [_ _] true)]
        (let [square :e4
              test-game (-> initial-game
                            (assoc :from-square :e2))
              game (square-selected-handler {:game test-game
                                             :square square
                                             :on-valid-move (fn []   )
                                             :on-valid-move-callback (fn []   )
                                             })]
          (is (= [[:e2 :e4]] (:half-moves game)))
          (is (nil? (:from-square game)))
          (is (= :e4 (:to-square game)))
          (is (:stalemate? game))
          )
        ))

    )
  )

