(ns chess.position.build
  (:require [chess.position.update :refer [update-position]]
            [chess.position.in-check :refer [is-king-in-check?]]
            ))

(defn build-position [initial-position half-moves]
  (let [!position (atom initial-position)]
    (doseq [half-move half-moves]

      ;; (swap! !position update-position half-move)

      (swap! !position (fn [position]
                         (let [new-position (update-position position half-move)]
                           (cond
                             (is-king-in-check? new-position :white)
                             (-> new-position
                                 (update-vals (fn [piece]
                                                (if (and (= :white (:colour piece))
                                                         (= :king (:type piece)))
                                                  (-> piece
                                                      (dissoc :can-still-castle?))
                                                  piece))))

                             (is-king-in-check? new-position :black)
                             (-> new-position
                                 (update-vals (fn [piece]
                                                (if (and (= :black (:colour piece))
                                                         (= :king (:type piece)))
                                                  (-> piece
                                                      (dissoc :can-still-castle?))
                                                  piece))))

                             :else
                             new-position)))))
    @!position))

