(ns memebrane.cas.lotka-volterra)

(defn lotka-volterra [a b c d e f x y]
  (let [xy (* x y)
        x' (/ (- (* a x) (* b xy))
              (+ 1 (* c x)))
        y' (/ (+ (* d y) (* e xy))
              (+ 1 (* f y)))]
    [x' y']))

(defn lotka-volterra-seq [a b c d e f x0 y0]
  (let [lv (partial lotka-volterra a b c d e f)]
    (iterate #(apply lv %) [x0 y0])))

(defn xy [a b c d e f x0 y0 t]
  (->> (lotka-volterra-seq a b c d e f x0 y0)
       (drop t)
       first))

(defn x [a b c d e f x0 y0 t]
  (first (xy a b c d e f x0 y0 t)))

(defn y [a b c d e f x0 y0 t]
  (second (xy a b c d e f x0 y0 t)))





