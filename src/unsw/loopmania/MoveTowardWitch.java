package unsw.loopmania;

public class MoveTowardWitch implements MoveBehaviour {
    private PathPosition pathPosition;

    public MoveTowardWitch(PathPosition pathPosition){
        this.pathPosition = pathPosition;
    }

    public void move(LoopManiaWorld world) {
        int myLocation = pathPosition.getCurrentPositionInPath();
        if (myLocation > world.getBossPosition()) {
            pathPosition.moveUpPath();
        } else {
            pathPosition.moveDownPath();
        } 
    }
}