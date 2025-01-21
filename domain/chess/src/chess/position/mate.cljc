(ns chess.position.mate
  (:require [chess.moves.valid-moves :refer [valid-moves]]
            [chess.position.in-check :refer [is-king-in-check?]]
            [chess.position.update :refer [update-position]]))

(defn is-checkmate? [position colour-to-move]

  ;; (println "----")
  ;; (println (is-king-in-check? position colour-to-move))
  ;; (println (valid-moves position colour-to-move))
  ;; (println (update-position position [:e1 :e2]))
  ;; #_(println
  ;;                     (map #(not (is-king-in-check?
  ;;                                 (update-position position %)
  ;;                                 colour-to-move))
  ;;                          (valid-moves position colour-to-move))
  ;;                     )
  ;; (println "====")

  (and (is-king-in-check? position colour-to-move)
       (not (some identity
                  (map #(not (is-king-in-check?
                              (update-position position %)
                              colour-to-move))
                       (valid-moves position colour-to-move))
                  ))

       #_(zero? (count (filter #(not (is-king-in-check?
                                      (update-position position %)
                                      colour-to-move))
                               (valid-moves position colour-to-move)))))

  #_(and (is-king-in-check? position colour-to-move)
       (zero? (count (filter #(not (is-king-in-check?
                                    (update-position position %)
                                    colour-to-move))
                             (valid-moves position colour-to-move))))))

(defn is-stalemate? [position colour-to-move]

  ;; should this be just 'are there no valid moves'?
  ;; but then valid_moves would need to be testing for not moving into check etc
  ;; (which is perhaps why we had the -two versions of things)

  ;; I think this issue here is that it is taking too long basically
  ;; (but not 100% sure about that)

  ;; could we do something like (apply and ...)
  ;; in a way that is 'lazy' - i.e. as soon as it can see there is at least one
  ;; valid move, it exits`

  (and (not (is-king-in-check? position colour-to-move))
       (not (some identity
                  (map #(not (is-king-in-check?
                              (update-position position %)
                              colour-to-move))
                       (valid-moves position colour-to-move))
                  ))

       #_(zero? (count (filter #(not (is-king-in-check?
                                    (update-position position %)
                                    colour-to-move))
                             (valid-moves position colour-to-move)))))

  #_(and (not (is-king-in-check? position colour-to-move))
       (zero? (count (filter #(not (is-king-in-check?
                                    (update-position position %)
                                    colour-to-move))
                             (valid-moves position colour-to-move))))))

#_(defn is-stalemate? [position colour]
  false)

