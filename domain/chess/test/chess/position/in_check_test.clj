(ns chess.position.in-check-test
  (:require [clojure.test :refer :all]
            [chess.position.in-check :refer :all]))

(deftest is-king-in-check-test
  (let [white-king   {:type :king
                      :colour :white}
        black-pawn   {:type :pawn
                      :colour :black}
        black-king   {:type :king
                      :colour :black}]
    (testing "Not in check"
      (is (false? (is-king-in-check? {:e1 white-king
                                      :d7 black-pawn}
                                     :white))))

    (testing "In check from pawn"
      (is (true? (is-king-in-check? {:e1 white-king
                                     :f2 black-pawn
                                     :h2 black-pawn}
                                    :white)))
      (is (is-king-in-check? {:f5 white-king  ;; had 'true' as third item in vector
                              :g6 black-pawn  ;; had 'false' as third item in vector
                              :f7 black-king}
                             :white)))))

