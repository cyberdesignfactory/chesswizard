(ns wizard.score.material)

(defn piece-score [piece-type]
  (case piece-type
    :pawn 1
    :knight 3
    :bishop 3
    :rook 5
    :queen 9
    :king 0))

(defn position-score [position colour]
  (let [white-pieces (filter #(= :white (:colour %)) (vals position))
        white-material (apply + (map #(piece-score (:type %)) white-pieces))
        black-pieces (filter #(= :black (:colour %)) (vals position))
        black-material (apply + (map #(piece-score (:type %)) black-pieces))]
    (if (= :white colour)
      (- white-material black-material)
      (- black-material white-material))))

