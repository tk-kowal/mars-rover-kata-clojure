(ns rover-kata.core)

(defn create_rover [heading]
  {:heading heading :x 0 :y 0})

(defn get_heading_index [heading]
  (let [heading-indexes { :NORTH 0 :EAST 1 :SOUTH 2 :WEST 3}]
    (heading heading-indexes)))

(defn get_heading_by_index [index]
  (let [valid-headings { 0 :NORTH 1 :EAST 2 :SOUTH 3 :WEST}]
    (valid-headings index)))

(defn rotate
  [rover direction]
  (let [heading (:heading rover)
        delta (if (= direction :RIGHT) 1 -1)]
    (assoc rover :heading 
           (get_heading_by_index 
             (mod (+ (get_heading_index heading) delta) 4)))))

(defn move
  [rover direction]
  (let [heading (:heading rover)
        delta (if (= direction :FORWARD) 1 -1)]
    (cond (= heading :NORTH) (assoc rover :y (+ (:y rover) delta))
          (= heading :EAST) (assoc rover :x (+ (:x rover) delta))
          (= heading :SOUTH) (assoc rover :y (- (:y rover) delta))
          (= heading :WEST) (assoc rover :x (- (:x rover) delta)))))

(defn command
  [rover commands]
  (let [command-map { "f" #(move % :FORWARD)
                      "b" #(move % :BACK)
                      "r" #(rotate % :RIGHT)
                      "l" #(rotate % :LEFT)}]
   (loop [rover rover
          commands commands]
     (if (empty? commands)
       rover
       (recur ((command-map (str (first commands))) rover) (rest commands))))))
