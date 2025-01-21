(ns chess.moves.pawn
  (:require [chess.helpers :refer [square-occupied?
                                   file
                                   rank
                                   rank-difference
                                   reverse-colour
                                   add-rank]]))

(defn invalid-pawn-move? [move position colour]
  (let [[from to] move]
    (if (= 1 (abs (- (file to) (file from))))
      (if (or
            ;; taking normally
            (and (not (nil? (get position to)))
                 (= 1 (rank-difference from to colour)))
            ;; en passant
            (and (nil? (get position to))
                 (= 1 (rank-difference from to colour))
                 (let [opp-pawn (get position (add-rank to 1 (reverse-colour colour)))]
                   (and (not (nil? opp-pawn))
                        ;; (= :pawn (first opp-pawn))
                        (= :pawn (:type opp-pawn))

                        ;; (= (reverse-colour colour) (second opp-pawn))
                        (= (reverse-colour colour) (:colour opp-pawn))

                        ;; (true? (nth opp-pawn 2 false))
                        ;; (true? (:just-moved-two-spaces? opp-pawn))
                        (:just-moved-two-spaces? opp-pawn)

                        )))) ;;  did pawn move two spaces
        nil
        :move/invalid)

      ;; otherwise check if pawn is being moved forward one or two spaces
      ;; without being blocked
      ;; (can only be moved two spaces if from second rank)
      (if (and (= (file from) (file to))
               (nil? (get position to))
               (or (and (= 2 (rank-difference from to colour))
                        (or
                         (and (= :white colour) (= 2 (rank from)))
                         (and (= :black colour) (= 7 (rank from))))
                        (not (square-occupied? (add-rank from 1 colour) position))
                        (not (square-occupied? (add-rank from 2 colour) position)))
                   (and (= 1 (rank-difference from to colour))
                        (not (square-occupied? (add-rank from 1 colour) position)))))
        nil
        :move/invalid))))

