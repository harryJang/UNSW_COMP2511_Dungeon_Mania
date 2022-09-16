package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Tower building in the world
 */
public class TowerBuilding extends Building {
    private int shootingRange = 2;
    private String towerBuildingImage;
    public static final String TOWER = "TOWER";
    public TowerBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.towerBuildingImage = "src/images/tower.png";
    }
    
    @Override
    public String getImage() {
        return this.towerBuildingImage;
    }

    @Override
    public String getName() {
        return TOWER;
    }

    public int getShootingRadius() {
        return shootingRange;
    }

    public void attack(List<Enemy> enemy) {
        for(Enemy e: enemy){
            e.loseHealth(10);
        }
    }

    public void BasicAttackInfected(List<AlliedSoldier> enemy) {
        for(AlliedSoldier e: enemy){
            e.loseHealth(10);
        }
    }

}
