(ns wizard.move.minimax
  ;; we won't check if opponent's move would be moving into check
  ;; (for efficiency) - remove '-two' to use the 'stricter' way
  (:require
   [chess.position.in-check :refer [is-king-in-check?]]
   [chess.position.update :refer [update-position]]
   [chess.helpers :refer [reverse-colour]]
   ;; [chess.moves.valid-moves :refer [valid-moves]]
   [chess.moves.valid-moves-two :refer [valid-moves]]
   [wizard.score.material :refer [position-score]]
   [wizard.move.simple :refer [simple-move]]
   ))

(defn minimax-move [position colour moves-ahead]
  (if (zero? moves-ahead)
    (simple-move position colour)
    (nth
     (sort-by (fn [move]
                (let [resultant-position (update-position position move)
                      opponent-colour (reverse-colour colour)

                      ;; opponent-move (simple-move resultant-position  ;; FOR NOW
                      ;;                            opponent-colour)

                      opponent-move (minimax-move resultant-position
                                                  opponent-colour
                                                  (dec moves-ahead))
                      ]
                  (position-score (update-position resultant-position opponent-move)
                               opponent-colour)))

              (if (= 1 moves-ahead)  ;; if it's the ai's actual move, before recursing
                (filter #(not (is-king-in-check? (update-position position %) colour))
                        (valid-moves position colour))
                (valid-moves position colour)))
     0)))


