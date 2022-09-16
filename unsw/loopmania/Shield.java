package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an equipped or unequipped sword in the backend world
 */
public class Shield extends ProtectiveGear {
    private String shieldImage;
    static final String SHIELD = "SHIELD";
    //static final String type = "protective";
    static final Integer defense = 15;

    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.shieldImage = "src/images/shield.png";
    }

    public Integer getDefense() {
        return defense;
    }

    @Override
    public String getImage() {
        return this.shieldImage;
    }
    
    @Override
    public String getName() {
        return SHIELD;
    }

    @Override
    public void defend(AttackBehaviour attack) {
        int damage = (int) ((double)(attack.getDamage()) * ((double)(100-defense)/(double)100));
        attack.setDamage(damage);

        if(attack instanceof VampireAttack){
            VampireAttack vAttack = (VampireAttack) attack;
            vAttack.setCriticalChance(6);
        }
    } 

}

