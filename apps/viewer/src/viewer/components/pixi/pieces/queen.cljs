(ns viewer.components.pixi.pieces.queen
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-queen [{:keys [colour]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))

          (.drawCircle g 20 14 4)
          (.drawCircle g 30 14 4)
          (.drawCircle g 25 12 4)

          (.drawCircle g 25 24 8)
          (.drawRect   g 18 28 14 16))]
    [:> Graphics {:draw draw-piece}]))

