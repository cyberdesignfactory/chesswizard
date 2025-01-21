(ns viewer.components.pixi.pieces.rook
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-rook [{:keys [colour]}]
  (let [draw-piece (fn [^js/PIXI.Graphics g]
                     (.clear g)
                     (.beginFill g (if (= :black colour) 0x000000 0xffffff))
                     (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
                     (.drawRect g 14 10 22 8)
                     (.drawRect g 18 18 14 20)
                     (.drawRect g 14 34 22 8))]
    [:> Graphics {:draw draw-piece}]))

