(ns chess.position.update
  (:require [chess.helpers :refer [rank file add-rank]]))

(defn reset-all-pawns [position]
  (update-vals position
               (fn [piece]
                 (let [{type :type
                       colour :colour} piece]
                   (if (= :pawn type)
                     {:type type
                      :colour colour
                      :just-moved? false}
                     piece)))))

(defn update-position [position move]

  ;; Assume move is valid
  ;; (as that is checked for elsewhere)

  (let [[from to] move
        piece-moved (get position from)
        {type :type
         colour :colour} piece-moved]
    ;; * ALSO NEED PROMOTION OF PAWNS (to queen will do for now I think, to avoid extra UI) *

    (cond
      ;; check for en passant:
      ;; - piece moved is a pawn,
      ;; - it moves onto an adjacent file
      ;; - there is no piece already on the 'to' square
      (and (= :pawn type)
           (= 1 (abs (- (file from) (file to))))
           (nil? (get position to)))
      (-> position
          (dissoc from)
          (assoc to piece-moved)
          ;; remove taken piece (which is not on 'to' square)
          (dissoc (add-rank to -1 colour))
          )

      ;; check for a pawn that has moved two squares (so we can set the flag)
      (and (= :pawn type)
           (= 2 (abs (- (rank from) (rank to))))
           (nil? (get position to)))
      (-> position
          (dissoc from)
          (assoc to (-> piece-moved
                        (assoc :just-moved-two-spaces? true))))

      ;; check for a white pawn that needs to be promoted
      (and (= :pawn type)
           (= :white colour)
           (= 8 (rank to)))
      (-> position
          (dissoc from)
          (assoc to {:type :queen :colour colour}))

      ;; check for a black pawn that needs to be promoted
      (and (= :pawn type)
           (= :black colour)
           (= 1 (rank to)))
      (-> position
          (dissoc from)
          (assoc to {:type :queen :colour colour}))

      ;; check for white king castling left
      (and (= [:e1 :c1] move)
           (= :king type)
           (= :white colour)
           (:can-still-castle? piece-moved)
           (= {:type :rook
               :colour :white}
              (get position :a1)))
      (-> position
          (dissoc from)
          (dissoc :a1)
          (reset-all-pawns)
          (assoc to {:type :king
                     :colour :white})
          (assoc :d1 {:type :rook
                                   :colour :white}))

      ;; check for white king castling right
      (and (= [:e1 :g1] move)
           (= :king type)
           (= :white colour)
           (:can-still-castle? piece-moved)
           (= {:type :rook
               :colour :white}
               (get position :h1)))
      (-> position
          (dissoc from)
          (dissoc :h1)
          (reset-all-pawns)
          (assoc to {:type :king
                     :colour :white})
          (assoc :f1 {:type :rook
                                   :colour :white}))

      ;; check for black king castling left
      (and (= [:e8 :c8] move)
           (= :king type)
           (= :black colour)
           (:can-still-castle? piece-moved)
           (= {:type :rook
               :colour :black}
              (get position :a8)))
      (-> position
          (dissoc from)
          (dissoc :a8)
          (reset-all-pawns)
          (assoc to {:type :king
                     :colour :black})
          (assoc :d8 {:type :rook
                                   :colour :black}))

      ;; check for black king castling right
      (and (= [:e8 :g8] move)
           (= :king type)
           (= :black colour)
           (:can-still-castle? piece-moved)

           (= {:type :rook
               :colour :black}
              (get position :h8)))
      (-> position
          (dissoc from)
          (dissoc :h8)
          (reset-all-pawns)
          (assoc to {:type :king
                     :colour :black})
          (assoc :f8 {:type :rook
                      :colour :black}))

      :else
      (-> position
          (dissoc from)
          (assoc to piece-moved)))))

