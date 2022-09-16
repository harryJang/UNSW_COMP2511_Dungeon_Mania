package unsw.loopmania;

import java.util.Random;

public class BasiliskMove implements MoveBehaviour {
    private PathPosition pathPosition;

    public BasiliskMove(PathPosition pathPosition){
        this.pathPosition = pathPosition;
    }

    public void move(LoopManiaWorld world) {
        int directionChoice = (new Random()).nextInt(10);
        if (directionChoice == 0) {
            pathPosition.moveUpPath();
        } else if (directionChoice == 1) {
            pathPosition.moveDownPath();
        } else {
            return;
        }   
    }
}