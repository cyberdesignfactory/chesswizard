(ns chess.moves.bishop
  (:require [chess.helpers :refer [file rank]]
            [chess.position.intermediate-squares :refer [intermediate-squares]]))

;; can move later
(defn on-diagonal? [from-square to-square]
  (= (abs (- (file from-square) (file to-square)))
     (abs (- (rank from-square) (rank to-square)))))

(defn invalid-bishop-move? [move position colour]
  (let [[from-square to-square] move]
    ;; to-square must be on a diagonal with from-square
    ;; must be no pieces in between (except on to-square)
    ;; cannot be own colour piece on to square
    (if (and (on-diagonal? from-square to-square)
             (zero? (count (filter #(get position %) (intermediate-squares move))))
             (not= colour (:colour (get position to-square))))
      nil
      :move/invalid)))

