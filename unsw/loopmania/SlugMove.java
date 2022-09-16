package unsw.loopmania;

import java.util.Random;

/**
 * Move Slug back or forward
 */
public class SlugMove implements MoveBehaviour{

    private PathPosition pathPosition;

    public SlugMove(PathPosition pathPosition) {
        this.pathPosition = pathPosition;
    }

    @Override
    public void move(LoopManiaWorld world) {
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0) {
            pathPosition.moveUpPath();
        } else if (directionChoice == 1) {
            pathPosition.moveDownPath();
        }
    }
}
