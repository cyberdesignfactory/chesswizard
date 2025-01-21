(ns chess.position.build
  (:require [chess.position.update :refer [update-position]]))

(defn build-position [initial-position half-moves]
  (let [!position (atom initial-position)]
    (doseq [half-move half-moves]
      (swap! !position update-position half-move))
    @!position))

