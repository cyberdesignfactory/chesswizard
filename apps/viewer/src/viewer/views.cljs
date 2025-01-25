(ns viewer.views
  (:require
   [clojure.pprint :as pp]
   [re-frame.core :as rf]
   [reagent.core :as r]
   [chess.helpers :refer [reverse-colour]]
   [chess.position.build :refer [build-position]]
   [chess.position.initial :refer [initial-position]]
   [wizard.move.minimax :refer [minimax-move]]
   [viewer.subs :as subs]
   [viewer.events :as events]
   [viewer.handlers :as handlers]
   [viewer.components.pixi.board :refer [pixi-board]]
   [viewer.components.moves :refer [moves-panel]]))

(defn wizard-move [position colour]
  (minimax-move position colour 1))

(defn colour-to-move-from-moves [half-moves]
  (if (even? (count half-moves))
    :white
    :black))

(defn choose-colour-panel [!game !players]
  [:div.text-center.p-4
   [:h4 "Play as..."]
   [:button.btn.btn-light.m-2
    {:on-click #(reset! !players {:white :user
                                  :black :wizard})}
    "White"]
   [:button.btn.btn-dark.m-2
    {:on-click (fn []
                 (js/setTimeout
                  (fn []
                    (let [wizard-move (wizard-move (:initial-position @!game) :white)]
                      (swap! !game assoc :to-square (second wizard-move))
                      (swap! !game update :half-moves conj wizard-move)))
                  200)
                 (reset! !players {:white :wizard
                                   :black :user}))}
    "Black"]])

(defn status-panel [!game]
  [:h5.m-4
   (cond
     (= :white (:checkmate @!game))
     "White checkmates!"

     (= :black (:checkmate @!game))
     "Black checkmates!"

     (:stalemate? @!game)
     "Stalemate."

     :else
     (str (if (= :white (colour-to-move-from-moves (:half-moves @!game)))
            "White"
            "Black") " to move"))])

(defn board-panel [!game !players]
  (let [position (build-position (:initial-position @!game)
                                 (:half-moves @!game))]
    [pixi-board
     {:position position
      :from-square (:from-square @!game)
      :to-square (:to-square @!game)
      :reversed? (= :wizard (:white @!players))

      :on-square-selected
      (fn [square]
        (swap! !game
               (fn [game]
                 (handlers/square-selected-handler
                  {:game game
                   :square square
                   :on-valid-move
                   (fn [half-move]
                     (let [new-pos (build-position position [half-move])
                           colour (reverse-colour
                                   (colour-to-move-from-moves (:half-moves game)))]

                       (when (= :wizard (get @!players colour))
                         (let [
                               wizard-move (wizard-move new-pos colour)
                               ]
                           (swap! !game assoc :to-square (second wizard-move))
                           (swap! !game update :half-moves conj wizard-move)))))}))))}]))

(defn back-button [!game]
  [:button.btn.btn-primary.mt-3
   {:disabled (zero? (count (:half-moves @!game)))
    :on-click #(swap! !game
                      (fn [game]
                        (if (or (:checkmate game)
                                (:stalemate? game))
                          (-> game
                              (dissoc :to-square)
                              (dissoc :checkmate)
                              (dissoc :stalemate?)
                              (update :half-moves pop))
                          (-> game
                              (dissoc :to-square)
                              (update :half-moves pop)
                              (update :half-moves pop)))))}
   "Back"])

(defn play-again-button [!game !players]
  [:button.btn.btn-secondary
   {:on-click (fn []
                (reset! !players nil)
                (reset! !game {:initial-position (initial-position)
                               :half-moves []
                               :from-square nil
                               :to-square nil}))}
   "Play again"])

(defn inner-main-panel []
  (let [!players (r/atom nil)
        !game (r/atom {:initial-position (initial-position)
                       :half-moves []
                       :from-square nil
                       :to-square nil})]
    (fn []
      [:div
       [:div.text-center.bg-primary.pt-4.pb-1
        [:h2.mb-4.text-secondary "Chess Wizard"]]
       (if (nil? @!players)
         [choose-colour-panel !game !players]

         [:div.container.text-center.xxpt-4
          [status-panel !game]

          ;; Mobiles
          [:div.d-lg-none
           [board-panel !game !players]
           [back-button !game]
           [moves-panel (:half-moves @!game) (:initial-position @!game)]]

          ;; Desktops
          [:div.d-none.d-lg-block
           [:div.card.bg-light.p-3.m-3
            [:div.card-body.pb-1.text-center
             [board-panel !game !players]
             [back-button !game]]]
           [moves-panel (:half-moves @!game) (:initial-position @!game)]]

          (when (or (:checkmate @!game)
                    (:stalemate? @!game))
            [play-again-button !game !players])])])))

(defn main-panel []
  [inner-main-panel])

