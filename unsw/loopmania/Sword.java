package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Sword extends Weapon {
    private String swordImage;
    static final String SWORD = "SWORD";
    static final Integer damage = 30;
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.swordImage = "src/images/basic_sword.png";
    }    

    @Override
    public String getImage() {
        return this.swordImage;
    }
    
    @Override
    public String getName() {
        return SWORD;
    }
    
    @Override
    public void attackEnemies(Enemy enemy, int actualDamage) {
        enemy.loseHealth(actualDamage + damage);
    }
}
