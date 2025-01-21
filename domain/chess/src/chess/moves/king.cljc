(ns chess.moves.king
  (:require [chess.helpers :refer [file rank]]
            [chess.position.update :refer [update-position]]))

(defn invalid-king-move? [move position colour]
  ;;(println "IKM:" move position colour)
  (let [[from to] move
        piece (get position from)
        can-still-castle? (:can-still-castle? piece)

        white-rook {:type :rook
                    :colour :white}
        black-rook {:type :rook
                    :colour :black}]
    ;; Castling
    (cond
      (or
       (and (= :white colour)
            can-still-castle?
            (or (and (= [:e1 :c1] move)
                     (= white-rook (get position :a1))
                     (nil? (get position :b1))
                     (nil? (get position :d1)))
                (and (= [:e1 :g1] move)
                     (= white-rook (get position :h1))
                     (nil? (get position :f1)))))
       (and (= :black colour)
            can-still-castle?
            (or (and (= [:e8 :c8] move)
                     (= black-rook (get position :a8))
                     (nil? (get position :b8))
                     (nil? (get position :d8)))
                (and (= [:e8 :g8] move)
                     (= black-rook (get position :h8))
                     (nil? (get position :f8))))))
      ;; other criteria, e.g. no pieces on intermediate squares
      nil

      (and
       ;; cannot be own colour piece on 'to' square
       (not= colour (:colour (get position to)))
       ;; cannot move into check
       ;; (not (is-king-in-check? (board-after-move board move) colour))
       ;; must be one square away
       (or (and (= (file from) (file to))
                (= 1 (abs (- (rank from) (rank to)))))
           (and (= (rank from) (rank to))
                (= 1 (abs (- (file from) (file to)))))
           (and (= 1 (abs (- (rank from) (rank to))))
                (= 1 (abs (- (file from) (file to)))))))
      nil

      :else :move/invalid)))

