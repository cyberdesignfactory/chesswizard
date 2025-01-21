(ns chess.moves.pawn-test
  (:require [clojure.test :refer :all]
            [chess.moves.pawn :refer :all]))

(deftest invalid-pawn-test

  (let [
        white-pawn   {:type :pawn
                      :colour :white}
        white-knight  {:type :knight
                      :colour :white}
        white-king   {:type :king
                      :colour :white}

        black-pawn   {:type :pawn
                      :colour :black}
        ]

    (testing "Valid pawn move - one square forward"
      (let [move [:a2 :a3]
            position {:a2 white-pawn}]
        (is (nil? (invalid-pawn-move? move position :white)))))

    (testing "Valid pawn move - two squares forward"
      (let [move [:a2 :a4]
            position {:a2 white-pawn}]
        (is (nil? (invalid-pawn-move? move position :white)))))

    (testing "Valid pawn move - taking to the left"
      (is (nil? (invalid-pawn-move? [:d4 :c5]
                                    {:d4 white-pawn
                                     :c5 [:pawn :black]} :white))))
    (testing "Valid pawn move - taking to the right"
      (is (nil? (invalid-pawn-move? [:d4 :e5]
                                    {:d4 white-pawn
                                     :e5 [:pawn :black]} :white))))

    (testing "Valid pawn move - taking 'en passant'"
      (is (nil? (invalid-pawn-move? [:d4 :e5]
                                    {:d4 white-pawn
                                     :e4
                                     (-> black-pawn
                                         (assoc :just-moved-two-spaces?
                                                true))}
                                    :white))))

    (testing "Invalid move - cannot take 'en passant' unless opponent pawn had moved two spaces"
      (is (not (nil? (invalid-pawn-move? [:d4 :e5]
                                         {:d4 white-pawn
                                          :e4 black-pawn} :white)))))

    (testing "Invalid move - cannot take own king 'en passant' (BUGFIX)"
      (is (not (nil? (invalid-pawn-move? [:b7 :c6]
                                         {:b7 [:pawn :black]
                                          :c7 [:king :black true]
                                          :d5 [:knight :white]}
                                         :black)))))

    (testing "Pawn blocked from moving forward one square"
      (is (not (nil? (invalid-pawn-move? [:e2 :e3]
                                         {:e2 white-pawn
                                          :e3 [:pawn :black]} :white)))))

    (testing "Pawn blocked from moving forward two squares by piece one square ahead"
      (is (not (nil? (invalid-pawn-move? [:e2 :e4]
                                         {:e2 white-pawn
                                          :e3 [:pawn :black]} :white)))))

    (testing "Pawn blocked from moving forward two squares by piece two squares ahead"
      (is (not (nil? (invalid-pawn-move? [:e2 :e4]
                                         {:e2 white-pawn
                                          :e4 [:pawn :black]} :white)))))

    (testing "Invalid destination square"
      (is (not (nil? (invalid-pawn-move? [:e2 :d7]
                                         {:e2 white-pawn
                                          :e4 [:pawn :black]} :white)))))

    (testing "Invalid pawn move - two squares forward, but not from second rank"
      (let [move [:a3 :a5]
            position {:a3 white-pawn}]
        (is (not (nil? (invalid-pawn-move? move position :white)))))))

  )


