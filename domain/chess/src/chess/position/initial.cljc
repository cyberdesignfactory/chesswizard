(ns chess.position.initial)

(defn initial-position []
  {
   :a1 {:type :rook :colour :white}
   :b1 {:type :knight :colour :white}
   :c1 {:type :bishop :colour :white}
   :d1 {:type :queen :colour :white}
   :e1 {:type :king :colour :white :can-still-castle? true}
   :f1 {:type :bishop :colour :white}
   :g1 {:type :knight :colour :white}
   :h1 {:type :rook :colour :white}

   :a2 {:type :pawn :colour :white}
   :b2 {:type :pawn :colour :white}
   :c2 {:type :pawn :colour :white}
   :d2 {:type :pawn :colour :white}
   :e2 {:type :pawn :colour :white}
   :f2 {:type :pawn :colour :white}
   :g2 {:type :pawn :colour :white}
   :h2 {:type :pawn :colour :white}

   :a8 {:type :rook :colour :black}
   :b8 {:type :knight :colour :black}
   :c8 {:type :bishop :colour :black}
   :d8 {:type :queen :colour :black}
   :e8 {:type :king :colour :black :can-still-castle? true}
   :f8 {:type :bishop :colour :black}
   :g8 {:type :knight :colour :black}
   :h8 {:type :rook :colour :black}

   :a7 {:type :pawn :colour :black}
   :b7 {:type :pawn :colour :black}
   :c7 {:type :pawn :colour :black}
   :d7 {:type :pawn :colour :black}
   :e7 {:type :pawn :colour :black}
   :f7 {:type :pawn :colour :black}
   :g7 {:type :pawn :colour :black}
   :h7 {:type :pawn :colour :black}

   })


