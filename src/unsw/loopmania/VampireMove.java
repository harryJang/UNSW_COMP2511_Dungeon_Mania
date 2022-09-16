package unsw.loopmania;

import java.util.List;
import java.util.Random;

public class VampireMove implements MoveBehaviour {

    private PathPosition pathPosition;

    public VampireMove(PathPosition pathPosition){
        this.pathPosition = pathPosition;
    }

    @Override
    public void move(LoopManiaWorld world) {
        List<Building> campfireBuilding = world.getBuildingListByName("CAMPFIRE");
        int directionChoice = (new Random()).nextInt(2);
        
        if (directionChoice == 0) {
            pathPosition.moveUpPath();
            for (Building cp : campfireBuilding ){
                if(Math.pow((cp.getX()-pathPosition.getX().get()), 2) +  Math.pow((cp.getY()-pathPosition.getY().get()), 2) < 4){
                    pathPosition.moveDownPath();
                }
            }
        } else if (directionChoice == 1) {
            pathPosition.moveDownPath();
            for (Building cp : campfireBuilding ){
                if(Math.pow((cp.getX()-pathPosition.getX().get()), 2) +  Math.pow((cp.getY()-pathPosition.getY().get()), 2) < 4){
                    pathPosition.moveUpPath();
                }
            }
        }
    }
}
    
