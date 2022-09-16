package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;

public class WitchAttack implements AttackBehaviour{

    private int battleRadius = 4;
    private int supportRadius = 0;
    private int basicDamage = 40;

    @Override
    public void attack (Character character) {
        character.loseHealth(this);
        if (character.getSizeofAlly() > 0) {
            List<AlliedSoldier> deadSoldier = new ArrayList<AlliedSoldier>();
            for (AlliedSoldier as : character.getAlly()) {
                as.loseHealth(30);
                if (as.getHealth() <= 0) {
                    deadSoldier.add(as);
                }
            }
            character.removeListofAlly(deadSoldier);
        }
    }
    @Override
    public int getBattleRadius(){
        return battleRadius;
    }
    @Override
    public int getSupportRadius(){
        return supportRadius;
    }
    @Override
    public int getDamage(){
        return basicDamage;
    }

    @Override
    public void setDamage(int damage){
        this.basicDamage = damage;
    }

    @Override
    public void setOrigin() {
        basicDamage = 40;
    }
    @Override
    public void setWitchCraftAfterEffect(double i) {
        basicDamage -= (int) ((double) basicDamage * i);
    }
}
