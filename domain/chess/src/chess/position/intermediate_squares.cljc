(ns chess.position.intermediate-squares
  (:require [chess.helpers :refer [rank file make-square]]))

(defn intermediate-squares [move]
  (let [[from to] move]
    (cond
      ;; along a file
      (= (file from) (file to))
      (let [;; lowest-rank (min (rank from) (rank to))
            ;; highest-rank (max (rank from) (rank to))
            ;; intermediate-ranks (range (inc lowest-rank) highest-rank)
            intermediate-ranks (if (> (rank from) (rank to))
                                 (range (dec (rank from)) (rank to) -1)
                                 (range (inc (rank from)) (rank to)))]
        (map #(make-square (file from) %) intermediate-ranks))

      ;; along a rank
      (= (rank from) (rank to))
      (let [;; lowest-file (min (file from) (file to))
            ;; highest-file (max (file from) (file to))
            ;; intermediate-files (range (inc lowest-file) highest-file)
            intermediate-files (if (> (file from) (file to))
                                 (range (dec (file from)) (file to) -1)
                                 (range (inc (file from)) (file to)))]
        (map #(make-square % (rank from)) intermediate-files))

      ;; along a diagonal
      (= (abs (- (file from) (file to)))
         (abs (- (file from) (file to))))
      (let [intermediate-files (if (> (file from) (file to))
                                 (range (dec (file from)) (file to) -1)
                                 (range (inc (file from)) (file to)))
            intermediate-ranks (if (> (rank from) (rank to))
                                 (range (dec (rank from)) (rank to) -1)
                                 (range (inc (rank from)) (rank to)))]
        (for [n (range (count intermediate-files))]
          (make-square (nth intermediate-files n)
                       (nth intermediate-ranks n)))))))

