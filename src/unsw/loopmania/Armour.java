package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Armour extends ProtectiveGear {

    private String armourImage;
    static final String ARMOUR = "ARMOUR";
    static final Integer defense = 50;

    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.armourImage = "src/images/armour.png";
    }    

    public Integer getDefense() {
        return defense;
    }

    @Override
    public String getImage() {
        return this.armourImage;
    }
    
    @Override
    public String getName() {
        return ARMOUR;
    }

    @Override
    public void defend(AttackBehaviour attack) {
        int damage = (int) ((double)(attack.getDamage()) * ((double)(100-defense)/(double)100));
        attack.setDamage(damage);

    }
}
