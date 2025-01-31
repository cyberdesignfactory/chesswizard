(ns viewer.components.pixi.pieces.rook
  (:require ["@pixi/react" :refer [Container Graphics]]))

(defn pixi-rook [{:keys [colour scale-factor]}]
  (let [draw-piece (fn [^js/PIXI.Graphics g]
                     (.clear g)
                     (.beginFill g (if (= :black colour) 0x000000 0xffffff))
                     (.lineStyle g 2 (if (= :black colour) 0x888888 0x444444))
                     ;; (.drawRect g 14 10 22 8)
                     ;; (.drawRect g 18 18 14 20)
                     ;; (.drawRect g 14 34 22 8)
                     (.drawRect g
                                (* 14 (or scale-factor 1.0))
                                (* 10 (or scale-factor 1.0))
                                (* 22 (or scale-factor 1.0))
                                (* 8 (or scale-factor 1.0))
                                )
                     (.drawRect g
                                (* 18 (or scale-factor 1.0))
                                (* 18 (or scale-factor 1.0))
                                (* 14 (or scale-factor 1.0))
                                (* 20 (or scale-factor 1.0))
                                )
                     (.drawRect g
                                (* 14 (or scale-factor 1.0))
                                (* 34 (or scale-factor 1.0))
                                (* 22 (or scale-factor 1.0))
                                (* 8 (or scale-factor 1.0))
                                )
                     )]
    [:> Graphics {:draw draw-piece}]))

