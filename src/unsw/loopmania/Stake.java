package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Stake extends Weapon {
    private String stakeImage;
    static final Integer damage = 20;
    public static final String STAKE = "STAKE";
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.stakeImage = "src/images/stake.png";
    }    
    
    @Override
    public String getImage() {
        return this.stakeImage;
    }
    
    @Override
    public String getName() {
        return STAKE;
    }

    /**
     *  Stake causes 2 times damage to vampire
     * @param enemy
     * @param actualDamage
     */
    @Override
    public void attackEnemies(Enemy enemy, int actualDamage) {
        if (enemy instanceof Vampire) {
            enemy.loseHealth(actualDamage + 2 * damage);
        } else {
            enemy.loseHealth(actualDamage + damage);
        }
    }
}

