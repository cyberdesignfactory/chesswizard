(ns viewer.components.pixi.board
  (:require [reagent.core :as r]
            ["@pixi/react" :refer [Stage Container Sprite Graphics]]
            [viewer.components.pixi.square :refer [pixi-square]]))

(defn pixi-board [{:keys [position
                          reversed?
                          from-square
                          scale-factor
                          to-square
                          on-square-selected]}]
  (let [
        ;; width 198 ;; 396;;200 ;; 396
        ;; height 198 ;; 396
        width (* 396 (or scale-factor 1.0))
        height (* 396 (or scale-factor 1.0))
        ]
    (fn [{:keys [position
                 reversed?
                 from-square
                 scale-factor
                 to-square
                 on-square-selected]}]
      ;; (js/alert (.-innerWidth js/window))
      [:div {:style {:cursor :pointer}}
       [:> Stage {:width width
                  :height height
                  :options {:background-color 0x884444}}
        (for [file (map inc (range 8))
              rank (map inc (range 8))]
          (let [name (keyword (str (char (+ 96 file)) rank))
                ;; x (* (/ 396 8) (dec file))
                ;; y (* (/ 396 8) (- 8 rank))
                x (if reversed?
                    (* (/ width 8) (- 7 (dec file)))
                    (* (/ width 8) (dec file)))
                y (if reversed?
                    (* (/ height 8) (dec rank))
                    (* (/ height 8) (- 8 rank)))
                square-colour (if (= 1 (mod (+ file rank) 2))
                                :white
                                :black)]
            [pixi-square {:key name
                          :name name
                          :pos {:x x :y y}
                          :square-colour square-colour
                          :scale-factor scale-factor
                          :piece (get position name)
                          :selected-from? (= name from-square)
                          :selected-to? (= name to-square)
                          :on-square-selected #(on-square-selected %)
                          }]))]])))

