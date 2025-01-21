(ns viewer.components.pixi.square
  (:require ["@pixi/react" :refer [Container Graphics]]
            ["@pixi/events"]
            [viewer.components.pixi.pieces.king :refer [pixi-king]]
            [viewer.components.pixi.pieces.queen :refer [pixi-queen]]
            [viewer.components.pixi.pieces.rook :refer [pixi-rook]]
            [viewer.components.pixi.pieces.bishop :refer [pixi-bishop]]
            [viewer.components.pixi.pieces.knight :refer [pixi-knight]]
            [viewer.components.pixi.pieces.pawn :refer [pixi-pawn]]
            ))

(defn pixi-square [{:keys [name
                           pos
                           square-colour
                           piece
                           selected-from?
                           selected-to?
                           on-square-selected
                           ]}]
  [:> Container {:x (get pos :x 0)
                 :y (get pos :y 0)}
   (let [draw-square (fn [^js/PIXI.Graphics g]
                       (.clear g)
                       (.beginFill g (if (= :white square-colour)
                                       0xccaa33
                                       0x773300))
                       (.lineStyle g 2 0x444444)
                       (.drawRect g 0 0 50 50)
                       (.endFill g))

         draw-selected-from-square (fn [^js/PIXI.Graphics g]
                                     (.clear g)
                                     (.beginFill g (if (= :white square-colour)
                                                     0xff0000
                                                     0xff0000))
                                     (.lineStyle g 2 0x444444)
                                     (.drawRect g 0 0 50 50)
                                     (.endFill g))

         draw-selected-to-square (fn [^js/PIXI.Graphics g]
                                   (.clear g)
                                   (.beginFill g (if (= :white square-colour)
                                                   0x0000ff
                                                   0x0000ff))
                                   (.lineStyle g 2 0x444444)
                                   (.drawRect g 0 0 50 50)
                                   (.endFill g))

         square-select (fn [event]
                         ;; (re-frame/dispatch [::events/square-selected name])
                         (on-square-selected name)
                         )
         ]

     [:> Graphics {:draw (if selected-from? draw-selected-from-square
                             (if selected-to? draw-selected-to-square
                                 draw-square))
                   ;; :hit-area (js/PIXI.Rectangle. 0 0 50 50)
                   :pointerdown square-select
                   :interactive true}])

   (if (not (nil? piece))
     (let [{:keys [type colour]} piece]
       (case type
         :king [pixi-king {:colour colour}]
         :queen [pixi-queen {:colour colour}]
         :rook [pixi-rook {:colour colour}]
         :bishop [pixi-bishop {:colour colour}]
         :knight [pixi-knight {:colour colour}]
         :pawn [pixi-pawn {:colour colour}]))
     nil)])

