package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Anduril extends Weapon {

    private String andurilImage;
    static final public String ANDURIL = "ANDURIL";
    static final Integer damage = 50;


    public Anduril(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.andurilImage = "src/images/anduril_flame_of_the_west.png";
    }   


    @Override
    public String getImage() {
        return this.andurilImage;
    }
    
    @Override
    public String getName() {
        return ANDURIL;
    }

    /**
     *  Stake causes 3 times damage to bosses
     * @param enemy
     * @param actualDamage
     */
    @Override
    public void attackEnemies(Enemy enemy, int actualDamage) {
        if (enemy instanceof Boss) {
            enemy.loseHealth((actualDamage + damage)*3);
        } else {
            enemy.loseHealth(actualDamage + damage);
        }
    }
}
