package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Staff extends Weapon {

    private String staffImage;
    static final String STAFF = "STAFF";
    static final Integer damage = 15;

    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.staffImage = "src/images/staff.png";
    }   

    @Override
    public String getImage() {
        return this.staffImage;
    }
    
    @Override
    public String getName() {
        return STAFF;
    }

    @Override
    public void attackEnemies(Enemy enemy, int actualDamage) {
        enemy.loseHealth(actualDamage + damage);
    }
}
