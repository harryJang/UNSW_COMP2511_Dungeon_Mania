package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class TreeStump extends ProtectiveGear {

    private String treeStumpImage;
    static final public String TREESTUMP = "TREESTUMP";
    static final Integer defense = 15;

    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.treeStumpImage = "src/images/tree_stump.png";
    }    

    public Integer getDefense() {
        return defense;
    }

    @Override
    public String getImage() {
        return this.treeStumpImage;
    }
    
    @Override
    public String getName() {
        return TREESTUMP;
    }

    @Override
    public void defend(AttackBehaviour attack) {
        int damage = (int) ((double)(attack.getDamage()) * ((double)(100-defense)/(double)100));
        attack.setDamage(damage);

        if (attack instanceof BasiliskAttack || attack instanceof WitchAttack){
            attack.setDamage((int) ((double)(attack.getDamage()) * ((double)(100-(double)defense*(double)4)/(double)100)));
        }
    } 
}