package unsw.loopmania;

import java.util.List;

public class Vampire extends BasicEnemy{
    private String vampireImage;

    public Vampire(PathPosition position){
        super(position);
        setHealth(150);
        setAttackBehaviour(new VampireAttack());
        setMoveBehaviour(new VampireMove(position));
        setName("VAMPIRE");
        this.vampireImage = "src/images/vampire.png";
    }
    
    public void runawayFromFire(List<Building> campfireBuilding){

        for (Building cp : campfireBuilding ){
            if((Math.pow((cp.getX()-getX()), 2) +  Math.pow((cp.getY()-getY()), 2) < 4) && (getX() < cp.getX() || getY() > cp.getY())){
                moveUpPath();
            }else if((Math.pow((cp.getX()-getX()), 2) +  Math.pow((cp.getY()-getY()), 2) < 4)){
                moveDownPath();
            }
        
        }
    }
    @Override
    public String getImage() {
        return this.vampireImage;
    }

    @Override
    public void setOriginalMoveBehaviour() {
        setMoveBehaviour(new VampireMove(super.getPathPosition()));
    }
}


