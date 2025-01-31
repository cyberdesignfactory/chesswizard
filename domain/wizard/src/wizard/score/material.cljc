(ns wizard.score.material)

;; No longer used - has been superseded by wizard.score.position

(defn piece-score [piece-type]
  (case piece-type
    :pawn 1
    :knight 3
    :bishop 3
    :rook 5
    :queen 9
    :king 0))

(defn material-score [position colour]
  (let [white-pieces (filter #(= :white (:colour %)) (vals position))
        white-material (apply + (map #(piece-score (:type %)) white-pieces))
        black-pieces (filter #(= :black (:colour %)) (vals position))
        black-material (apply + (map #(piece-score (:type %)) black-pieces))]
    (if (= :white colour)
      (- white-material black-material)
      (- black-material white-material))))

