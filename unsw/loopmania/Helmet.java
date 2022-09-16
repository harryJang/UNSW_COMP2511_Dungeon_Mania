package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Helmet extends ProtectiveGear {

    private String helmetImage;
    static final Integer defense = 3;
    public static final String HELMET = "HELMET";
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.helmetImage = "src/images/helmet.png";
    }    

    public Integer getDefense() {
        return defense;
    }

    @Override
    public String getImage() {
        return this.helmetImage;
    }
    
    @Override
    public String getName() {
        return HELMET;
    }

    @Override
    public void defend(AttackBehaviour attack) {
        int damage = attack.getDamage() - defense;
        attack.setDamage(damage);

    }

}
