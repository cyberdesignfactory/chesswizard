(ns chess.moves.knight-test
  (:require [clojure.test :refer :all]
            [chess.moves.knight :refer :all]))

(deftest invalid-knight-test
  (let [white-knight {:type :knight
                      :colour :white}]
    (testing "Valid knight move"
      (let [move [:a2 :b4]
            position {:a2 white-knight}]
        (is (nil? (invalid-knight-move? move position :white)))))

    (testing "Invalid knight move"
      (let [move [:a2 :b5]
            position {:a2 white-knight}]
        (is (not (nil? (invalid-knight-move? move position :white))))))))

