(ns viewer.components.pixi.pieces.pawn
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-pawn [{:keys [colour]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
          (.drawRect g 18 25 12 12)
          (.drawCircle g 24 20 6))]
    [:> Graphics {:draw draw-piece}]))

