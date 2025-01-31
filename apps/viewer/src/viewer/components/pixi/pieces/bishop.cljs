(ns viewer.components.pixi.pieces.bishop
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-bishop [{:keys [colour scale-factor]}]
  (let [draw-piece
        (fn [^js/PIXI.Graphics g]
          (.clear g)
          (.beginFill g (if (= :black colour) 0x000000 0xffffff))
          (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
          ;; (.drawCircle g 25 12 4)
          ;; (.drawCircle g 25 24 8)
          ;; (.drawRect   g 18 28 14 16)
          (.drawCircle g
                       (* 25 (or scale-factor 1.0))
                       (* 12 (or scale-factor 1.0))
                       (* 4 (or scale-factor 1.0))
                       )
          (.drawCircle g
                       (* 25 (or scale-factor 1.0))
                       (* 24 (or scale-factor 1.0))
                       (* 8 (or scale-factor 1.0))
                       )
          (.drawRect g
                     (* 18 (or scale-factor 1.0))
                     (* 28 (or scale-factor 1.0))
                     (* 14 (or scale-factor 1.0))
                     (* 16 (or scale-factor 1.0))
                     )

          )]
    [:> Graphics {:draw draw-piece}]))

