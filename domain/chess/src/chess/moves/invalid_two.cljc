(ns chess.moves.invalid-two
  (:require
   ;; [chess.position.in-check :refer [is-king-in-check?]]
   [chess.moves.pawn :refer [invalid-pawn-move?]]
   [chess.moves.knight :refer [invalid-knight-move?]]
   [chess.moves.bishop :refer [invalid-bishop-move?]]
   [chess.moves.rook :refer [invalid-rook-move?]]
   [chess.moves.queen :refer [invalid-queen-move?]]
   [chess.moves.king :refer [invalid-king-move?]]))

(defn invalid-move? [move position colour-to-move]
  (let [[from to] move
        piece (get position from)
        {type :type
         colour :colour} piece]

    (cond

      ;; must be a piece on the 'from' square
      (nil? piece)
      :move.invalid/no-piece

      ;; cannot move opponent's piece
      (not= colour colour-to-move)
      :move.invalid/wrong-colour

      ;; cannot be own colour piece on 'to' square
      (= colour-to-move (:colour (get position to)))
      :move.invalid/cannot-take-own-piece

      (case type
        :pawn (invalid-pawn-move? move position colour-to-move)
        :knight (invalid-knight-move? move position colour-to-move)
        :bishop (invalid-bishop-move? move position colour-to-move)
        :rook (invalid-rook-move? move position colour-to-move)
        :queen (invalid-queen-move? move position colour-to-move)
        :king (invalid-king-move? move position colour-to-move)

        )
      :move/invalid

      ;; ;; cannot move into check (or not move out of check if in check)
      ;; (is-king-in-check? position colour-to-move)
      ;; :move/king-in-check

      :else nil)))
