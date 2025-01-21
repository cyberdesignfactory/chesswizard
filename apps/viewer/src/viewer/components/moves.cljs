(ns viewer.components.moves
  (:require [chess.position.update :refer [update-position]]
            [chess.notation.algebraic :refer [algebraic-move-notation]]))

(defn moves-panel [half-moves initial-board]
  (let [!notation-half-moves (atom [])
        !current-position (atom initial-board)]
    (doseq [half-move half-moves]
      (swap! !notation-half-moves conj (algebraic-move-notation half-move @!current-position))
      (swap! !current-position #(update-position % half-move)))
    (let [notation-full-moves
          (apply str
                 (flatten
                  (interpose ",  "
                             (map #(interpose " " %)
                                  (partition 2 2 "" @!notation-half-moves)))))]
      [:div.card.bg-light.p-3.m-3
       [:p notation-full-moves]])))

