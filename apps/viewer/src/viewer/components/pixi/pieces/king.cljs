(ns viewer.components.pixi.pieces.king
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-king [{:keys [colour]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))

          (.drawRect g 22 8 6 14)
          (.drawRect g 18 12 14 6)

          (.drawRect g 18 22 14 24))]
    [:> Graphics {:draw draw-piece}]))

