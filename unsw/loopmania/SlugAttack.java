package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlugAttack implements AttackBehaviour{

    private int battleRadius = 2;
    private int supportRadius = 3;
    private int basicDamage = 10;
    
    @Override
    public void attack(Character character){
        Random rand = new Random();
        basicDamage = rand.nextInt(6) + basicDamage;
        
        character.loseHealth(this);
        int losthealth = rand.nextInt(6) + 10;
        if (character.getSizeofAlly() > 0) {
            List<AlliedSoldier> deadSoldier = new ArrayList<AlliedSoldier>();
            for (AlliedSoldier as : character.getAlly()) {
                as.loseHealth(losthealth);
                if (as.getHealth() <= 0) {
                    deadSoldier.add(as);
                }
            }
            character.removeListofAlly(deadSoldier);
        }
    }
    @Override
    public int getBattleRadius() {
        return battleRadius;
    }
    @Override
    public int getSupportRadius() {
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
    public void setOrigin(){
        basicDamage = 10;
    }
    @Override
    public void setWitchCraftAfterEffect(double i) {
        basicDamage -= (int) ((double) basicDamage * i);
    }
}
