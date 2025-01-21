(ns chess.moves.rook
  (:require [chess.helpers :refer [file rank]]
            [chess.position.intermediate-squares :refer [intermediate-squares]]))

(defn invalid-rook-move? [move board colour]
  (let [[from-square to-square] move]
    ;; to-square must be on a shared file or rank with from-square
    ;; must be no pieces in between (except on to-square)
    ;; (use helpers/intermediate-squares)
    ;; cannot be own colour piece on to square
    (if (and (or (= (file from-square) (file to-square))
                 (= (rank from-square) (rank to-square)))
             (zero? (count (filter #(get board %) (intermediate-squares move))))
             (not= colour (:colour (get board to-square))))
      nil
      :move/invalid)))

