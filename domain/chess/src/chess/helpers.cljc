(ns chess.helpers)

(defn square-occupied? [square position]
  (if (get position square) true false))

;; returns 1-8
(defn file [square]
  #?(:clj (- (int (first (name square))) 96)
     :cljs (- (.charCodeAt (name square) 0) 96)))

;; returns 1-8
(defn rank [square]
  #?(:clj (- (int (second (name square))) 48)
     :cljs (- (.charCodeAt (name square) 1) 48)))

(defn on-adjacent-files? [square-1 square-2]
  (= 1 (abs (- (file square-1) (file square-2)))))

(defn file-difference [from-square to-square]
  (- (file to-square) (file from-square)))

(defn rank-difference [from-square to-square colour]
  (if (= :white colour)
    (- (rank to-square) (rank from-square))
    (- (rank from-square) (rank to-square))))

(defn reverse-colour [colour] (if (= :white colour)
                                :black
                                :white))

(defn reverse-status [status] (if (= :status.to-move/white status)
                                :status.to-move/black
                                :status.to-move/white))

(defn make-square [file rank]
  (keyword (str (char (+ 96 file)) rank)))

(defn add-rank [square amount colour]
  (if (= :white colour)
    (make-square (file square) (+ (rank square) amount))
    (make-square (file square) (- (rank square) amount))))

(defn intermediate-squares [move]
  ;; move should be along one of:
  ;; - a rank
  ;; - a file
  ;; - a diagonal

  []  ;; FOR NOW

  )
