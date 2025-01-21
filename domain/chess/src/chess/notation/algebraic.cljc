(ns chess.notation.algebraic
  (:require [chess.helpers :refer [reverse-colour]]
            [chess.position.update :refer [update-position]]
            [chess.position.in-check :refer [is-king-in-check?]]
            [chess.position.mate :refer [is-checkmate?]]))

(defn algebraic-move-notation [half-move initial-position]
  (let [[from-square to-square] half-move
        moved-piece (get initial-position from-square)
        {:keys [type colour]} moved-piece

        ;; normally this is just whether there is a piece on the to square
        ;; except that pawns are a special case
        ;; - perhaps en passant is the only exception
        ;; FOR NOW - not dealing with en passant
        taking? (get initial-position to-square)

        new-position (update-position initial-position half-move)
        in-check? (is-king-in-check? new-position (reverse-colour colour))
        checkmate? (is-checkmate? new-position (reverse-colour colour))]
    ;; FOR NOW - we will not worry about 'disambiguation'
    (str
     (case (:type moved-piece)
       :pawn
       (if taking?
         (str (first (name from-square)) "x" (name to-square))
         (name to-square))

       :knight
       (if taking?
         (str "Nx" (name to-square))
         (str "N" (name to-square)))

       :bishop
       (if taking?
         (str "Bx" (name to-square))
         (str "B" (name to-square)))

       :rook
       (if taking?
         (str "Rx" (name to-square))
         (str "R" (name to-square)))

       :queen
       (if taking?
         (str "Qx" (name to-square))
         (str "Q" (name to-square)))

       :king
       (if taking?
         (str "Kx" (name to-square))
         (str "K" (name to-square)))

       ;; default
       (str (name from-square) " - " (name to-square)))
     (cond
       checkmate? "++"
       in-check? "+"
       :else ""))))

