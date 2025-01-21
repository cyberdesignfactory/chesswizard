(ns wizard.move.simple
  ;; we won't check if opponent's move would be moving into check
  ;; (for efficiency) - remove '-two' to use the 'stricter' way
  ;; (:require [domain.moves.valid-moves-two :refer [valid-moves]]
  (:require
   ;; [chess.moves.valid-moves :refer [valid-moves]]
   [chess.moves.valid-moves-two :refer [valid-moves]]
   [chess.position.update :refer [update-position]]
   [wizard.score.material :refer [position-score]]))

(defn simple-move [position colour]
  (nth
   (sort-by #(- (position-score (update-position position %)
                                colour))
            (valid-moves position colour))
   0
   nil))

