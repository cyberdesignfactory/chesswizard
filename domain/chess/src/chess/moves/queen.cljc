(ns chess.moves.queen
  (:require [chess.helpers :refer [file rank]]
            [chess.position.intermediate-squares :refer [intermediate-squares]]))

;; can move later
(defn on-diagonal? [from-square to-square]
  (= (abs (- (file from-square) (file to-square)))
     (abs (- (rank from-square) (rank to-square)))))

(defn invalid-queen-move? [move board colour]
  (let [[from-square to-square] move]
    ;; to-square must be on a shared file, rank or diagonal with from-square
    ;; must be no pieces in between (except on to-square)
    ;; (use helpers/intermediate-squares)
    ;; cannot be own colour piece on to square
    (if (and (or (= (file from-square) (file to-square))
                 (= (rank from-square) (rank to-square))
                 (on-diagonal? from-square to-square))
             (zero? (count (filter #(get board %) (intermediate-squares move))))
             (not= colour (:colour (get board to-square))))
      nil
      :move/invalid)))

