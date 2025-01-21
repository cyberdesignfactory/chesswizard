(ns chess.position.in-check
  (:require [chess.helpers :refer [make-square reverse-colour]]
            ;; [chess.moves.valid-moves-two :refer [valid-moves]]
            [chess.moves.valid-moves-two :refer [valid-moves]]
            ))

(defn is-king-in-check? [position colour]
  (true?
    ;; https://stackoverflow.com/questions/2969543/why-doesnt-apply-or-true-false-work-in-clojure
    ;; cannot use: (apply or ...)
    (some identity
          (for [opponent-move (valid-moves position (reverse-colour colour))]
            (let [[from to] opponent-move]
              (and
               (= :king (:type (get position to)))
               (= colour (:colour (get position to)))))))))

