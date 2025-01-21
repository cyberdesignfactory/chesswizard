(ns chess.position.mate-test
  (:require [clojure.test :refer :all]
            [chess.position.mate :refer :all]))

;; (deftest ^:test-refresh/focus mate-test
(deftest mate-test
  (testing "Checkmate"
    (let [position {:e1 {:type :king :colour :white}
                    :e2 {:type :queen :colour :black}
                    :h2 {:type :rook :colour :black}}]
      (is (is-checkmate? position :white))
      (is (not (is-checkmate? position :black)))))

  (testing "Should NOT be checkmate"
    (let [position {:e8 {:type :king :colour :black}
                    :f7 {:type :bishop :colour :white}
                    :e1 {:type :king :colour :white}}]
      (is (not (is-checkmate? position :white)))
      (is (not (is-checkmate? position :black)))))

  (testing "Stalemate"
    (let [position {:g1 {:type :king :colour :white}
                    :e2 {:type :queen :colour :black}
                    :h2 {:type :rook :colour :black}}]
      (is (is-stalemate? position :white))
      (is (not (is-stalemate? position :black)))))

  (testing "Neither"
    (let [position {:a1 {:type :king :colour :white}
                    :e2 {:type :queen :colour :black}
                    :h2 {:type :rook :colour :black}}]
      (is (not (is-checkmate? position :white)))
      (is (not (is-checkmate? position :black)))
      (is (not (is-stalemate? position :white)))
      (is (not (is-stalemate? position :black))))))

