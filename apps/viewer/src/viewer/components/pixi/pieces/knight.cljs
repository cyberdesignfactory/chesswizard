(ns viewer.components.pixi.pieces.knight
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-knight [{:keys [colour scale-factor]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
          ;; (.drawRect g 18 25 14 20)
          ;; (.drawCircle g 16 20 8)
          (.drawRect g
                     (* 18 (or scale-factor 1.0))
                     (* 25 (or scale-factor 1.0))
                     (* 14 (or scale-factor 1.0))
                     (* 20 (or scale-factor 1.0))
                     )
          (.drawCircle g
                       (* 16 (or scale-factor 1.0))
                       (* 20 (or scale-factor 1.0))
                       (* 8 (or scale-factor 1.0))
                       )

          )]
    [:> Graphics {:draw draw-piece}]))

