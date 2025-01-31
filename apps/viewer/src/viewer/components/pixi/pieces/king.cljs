(ns viewer.components.pixi.pieces.king
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-king [{:keys [colour scale-factor]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))

          ;; (.drawRect g 22 8 6 14)
          ;; (.drawRect g 18 12 14 6)

          ;; (.drawRect g 18 22 14 24)

          (.drawRect g
                     (* 22 (or scale-factor 1.0))
                     (* 8 (or scale-factor 1.0))
                     (* 6 (or scale-factor 1.0))
                     (* 14 (or scale-factor 1.0))
                     )
          (.drawRect g
                     (* 18 (or scale-factor 1.0))
                     (* 12 (or scale-factor 1.0))
                     (* 14 (or scale-factor 1.0))
                     (* 6 (or scale-factor 1.0))
                     )

          (.drawRect g
                     (* 18 (or scale-factor 1.0))
                     (* 22 (or scale-factor 1.0))
                     (* 14 (or scale-factor 1.0))
                     (* 24 (or scale-factor 1.0))
                     )

          )]
    [:> Graphics {:draw draw-piece}]))

