(ns viewer.handlers
  (:require [chess.position.build :refer [build-position]]
            [chess.position.mate :refer [is-checkmate? is-stalemate?]]
            [chess.moves.invalid :refer [invalid-move?]]
            [chess.helpers :refer [reverse-colour]]))

(defn colour-to-move-from-moves [half-moves]
  (if (even? (count half-moves))
    :white
    :black))

;; Perhaps it shouldn't let us select a square unless there is a piece on it?

(defn square-selected-handler [{:keys [game square on-valid-move on-valid-move-callback]}]
  (cond
    ;; if checkmate or statemate, do nothing
    (or false
        (:checkmate game)
        (:stalemate? game))
    game

    ;; if a 'from square' has not been selected, select it
    (nil? (:from-square game))
    ;; if we had a built position, we could check there is a piece of the right colour
    ;; on the selected (from) square...
    #_(and
     (nil? (:from-square game))
     (get position (:from-square-game)))
    (-> game
        (assoc :from-square square)
        (dissoc :to-square))

    ;; if it's the 'from square' that has been selected, deselect it
    (= square (:from-square game))
    (-> game
        (dissoc :from-square))

    :else
    ;; otherwise take it to mean the 'to square'
    (let [move [(:from-square game) square]
          ;; colour-to-move :white
          position (build-position (:initial-position game) (:half-moves game))
          colour-to-move (colour-to-move-from-moves (:half-moves game))
          invalid? (invalid-move? move position colour-to-move)]
      (if invalid?
        game ;; do nothing

        (let [new-position (build-position position [move])
              new-colour (reverse-colour colour-to-move)]
          (cond
            (is-checkmate? new-position new-colour)
            (-> game
                (assoc :to-square square)
                (update :half-moves conj move)
                (dissoc :from-square)
                (assoc :checkmate colour-to-move))

            (is-stalemate? new-position new-colour)
            (-> game
                (assoc :to-square square)
                (update :half-moves conj move)
                (dissoc :from-square)
                (assoc :stalemate? true))

            :else
            (do
              (js/setTimeout
               (fn []
                 ;; perhaps this can't easily be tested
                 ;; (on-valid-move move on-valid-move-callback)
                 (on-valid-move move ))
               200)
              (-> game
                  (assoc :to-square square)
                  (update :half-moves conj move)
                  (dissoc :from-square)))))))))

                ;; any way to remove the duplication?


