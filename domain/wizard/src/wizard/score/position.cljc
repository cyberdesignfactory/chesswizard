(ns wizard.score.position
  (:require [chess.helpers :refer [reverse-colour rank file]]
            [chess.position.mate :refer [is-checkmate? is-stalemate?]]))

(defn piece-score [piece square colour-to-move]
  (let [material-score (case (:type piece)
                         :pawn 1
                         :knight 3
                         :bishop 3
                         :rook 5
                         :queen 9
                         :king 0)
        rank-score (if (= :white (:colour piece))
                     (+ 1.0 (* (rank square) 0.016))
                     (+ 1.0 (* (- 9 (rank square)) 0.016)))
        file-score (condp = (file square)
                     1 1.0
                     2 1.02
                     3 1.04
                     4 1.06
                     5 1.06
                     6 1.04
                     7 1.02
                     8 1.0)]
    (* material-score
       rank-score
       file-score
       ;; subtract if it's the opponent's piece
       (if (= (:colour piece) colour-to-move)
         1.0
         -1.0))))

(defn inner-position-score [position colour]
  (cond

    ;; These currently take too long, so are commented out for now...

    ;; (is-checkmate? position colour)
    ;; 1000

    ;; (is-checkmate? position (reverse-colour colour))
    ;; -1000

    ;; (is-stalemate? position colour)
    ;; 0

    :else
    (apply +
           (map (fn [[square piece]]
                  (piece-score piece square colour))
                position))))

(def position-score (memoize inner-position-score))

