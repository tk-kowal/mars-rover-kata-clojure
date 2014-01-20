(ns rover-kata.core-spec
  (:require [speclj.core :refer :all]
            [rover-kata.core :refer :all]))

(describe "rover-kata.core"

  (with rover (create_rover :NORTH))
  
  (describe "create_rover"

    (it "Should be created with correct heading"
      (should= :NORTH (:heading @rover)))

    (it "Should initially have x and y of 0"
      (should= 0 (:x @rover) (:y @rover))))
  
  (describe "rotate :RIGHT"
    
    (it "Should rotate from :NORTH to :EAST"
      (let [rover (create_rover :NORTH)]
        (should= :EAST (:heading (rotate rover :RIGHT)))))
    
    (it "Should rotate from :EAST to :SOUTH"
      (let [rover (create_rover :EAST)]
        (should= :SOUTH (:heading (rotate rover :RIGHT)))))
    
    (it "Should rotate from :SOUTH to :WEST"
      (let [rover (create_rover :SOUTH)]
        (should= :WEST (:heading (rotate rover :RIGHT)))))
    
    (it "Should rotate from :WEST to :NORTH"
      (let [rover (create_rover :WEST)]
        (should= :NORTH (:heading (rotate rover :RIGHT))))))
  
  (describe "rotate :LEFT"
    
    (it "Should rotate from :NORTH to :WEST"
      (let [rover (create_rover :NORTH)]
        (should= :WEST (:heading (rotate rover :LEFT)))))
    
    (it "Should rotate from :WEST to :SOUTH"
      (let [rover (create_rover :WEST)]
        (should= :SOUTH (:heading (rotate rover :LEFT)))))
    
    (it "Should rotate from :SOUTH to :EAST"
      (let [rover (create_rover :SOUTH)]
        (should= :EAST (:heading (rotate rover :LEFT)))))
    
    (it "Should rotate from :EAST to :NORTH"
      (let [rover (create_rover :EAST)]
        (should= :NORTH (:heading (rotate rover :LEFT))))))
  
  (describe "move :FORWARD"
    
    (it "Should increment Y when facing :NORTH"
      (let [rover (create_rover :NORTH)]
        (should= 1 (:y (move rover :FORWARD)))))
    
    (it "Should increment X when facing :EAST"
      (let [rover (create_rover :EAST)]
        (should= 1 (:x (move rover :FORWARD)))))
    
    (it "Should decrement Y when facing :SOUTH"
      (let [rover (create_rover :SOUTH)]
        (should= -1 (:y (move rover :FORWARD)))))
    
    (it "Should decrement X when facing :WEST"
      (let [rover (create_rover :WEST)]
        (should= -1 (:x (move rover :FORWARD))))))
  
  (describe "move :BACK"
    
    (it "Should decrement Y when facing :NORTH"
      (let [rover (create_rover :NORTH)]
        (should= -1 (:y (move rover :BACK)))))
    
    (it "Should decrement X when facing :EAST"
      (let [rover (create_rover :EAST)]
        (should= -1 (:x (move rover :BACK)))))
    
    (it "Should increment Y when facing :SOUTH"
      (let [rover (create_rover :SOUTH)]
        (should= 1 (:y (move rover :BACK)))))
    
    (it "Should increment X when facing :WEST"
      (let [rover (create_rover :WEST)]
        (should= 1 (:x (move rover :BACK))))))
  
  (describe "command"
    
    (it "Should rotate rover right when given 'r'"
      (let [rover (create_rover :NORTH)]
        (should= :EAST (:heading (command rover "r")))))
    
    (it "Should rotate rover left when given 'l'"
      (let [rover (create_rover :NORTH)]
        (should= :WEST (:heading (command rover "l")))))
    
    (it "Should move rover forward 1 when given 'f'"
      (let [rover (create_rover :NORTH)]
        (should= 1 (:y (command rover "f")))))
    
    (it "Should move rover back 1 when given 'b'"
      (let [rover (create_rover :NORTH)]
        (should= -1 (:y (command rover "b")))))
    
    (it "Should rotate right and move forward 1 given 'rf'"
      (let [rover (create_rover :NORTH)]
        (should= 1 (:x (command rover "rf")))
        (should= :EAST (:heading (command rover "rf")))))
    
    (it "Should end up back where it started"
      (let [rover (create_rover :NORTH)
            final_position (command rover "ffrblbbrfl")]
        (should= 0 (:x final_position))
        (should= 0 (:y final_position))
        (should= :NORTH (:heading final_position))))))
  
    
