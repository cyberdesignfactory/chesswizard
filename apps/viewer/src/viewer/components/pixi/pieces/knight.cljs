(ns viewer.components.pixi.pieces.knight
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-knight [{:keys [colour]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
          (.drawRect g 18 25 14 20)
          (.drawCircle g 16 20 8))]
    [:> Graphics {:draw draw-piece}]))

