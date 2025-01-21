(ns chess.moves.valid-moves-two
  (:require [chess.helpers :refer [make-square]]
            ;; [chess.moves.invalid-move-two :refer [invalid-move?]]
            [chess.moves.invalid-two :refer [invalid-move?]]
            ))

;; we'll put these here for now, but maybe move them later

(defn all-squares []
  (for [file (map inc (range 8))
        rank (map inc (range 8))]
    (make-square file rank)))

(defn all-possible-moves []
  (for [from (all-squares)
        to (all-squares)]
    [from to]))

(defn valid-moves [board colour]
  (filter #(nil? (invalid-move? % board colour))
          (shuffle (all-possible-moves))))

