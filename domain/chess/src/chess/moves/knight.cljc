(ns chess.moves.knight
  (:require [chess.helpers :refer [file rank]]))

(defn invalid-knight-move? [move position colour]
  (let [[from-square to-square] move]
    (if (or (and (= 1 (abs (- (rank to-square) (rank from-square))))
                 (= 2 (abs (- (file to-square) (file from-square)))))
            (and (= 2 (abs (- (rank to-square) (rank from-square))))
                 (= 1 (abs (- (file to-square) (file from-square))))))
      nil
      :move/invalid)))

