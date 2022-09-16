package unsw.loopmania;

import java.util.Random;

public class ZombieMove implements MoveBehaviour {
    private PathPosition pathPosition;

    public ZombieMove(PathPosition pathPosition){
        this.pathPosition = pathPosition;
    }

    public void move(LoopManiaWorld world) {
        int directionChoice = (new Random()).nextInt(8);
        if (directionChoice == 0) {
            pathPosition.moveUpPath();
        } else if (directionChoice == 1) {
            pathPosition.moveDownPath();
        } else {
            return;
        }   
    }
}