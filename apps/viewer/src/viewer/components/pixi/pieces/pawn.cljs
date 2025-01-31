(ns viewer.components.pixi.pieces.pawn
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-pawn [{:keys [colour scale-factor]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
          ;; (.drawRect g 18 25 12 12)
          ;; (.drawCircle g 24 20 6)
          (.drawRect g
                     (* 18 (or scale-factor 1.0))
                     (* 25 (or scale-factor 1.0))
                     (* 12 (or scale-factor 1.0))
                     (* 12 (or scale-factor 1.0)))
          (.drawCircle g
                       (* 24 (or scale-factor 1.0))
                       (* 20 (or scale-factor 1.0))
                       (* 6 (or scale-factor 1.0))))]
    [:> Graphics {:draw draw-piece}]))

