package unsw.loopmania;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class VampireAttack implements AttackBehaviour{

    private int battleRadius = 4;
    private int supportRadius = 6;
    private int basicDamage = 25;
    private Random criticalChance = new Random();
    private int chance = 4;

    @Override
    public void attack (Character character){
        if(criticalChance.nextInt(chance) == 0){
            basicDamage = basicDamage*2;
        }

        character.loseHealth(this);

        if (character.getSizeofAlly() > 0) {
            List<AlliedSoldier> deadSoldier = new ArrayList<AlliedSoldier>();
            for (AlliedSoldier as : character.getAlly()) {
                as.loseHealth(20);
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
    public void setOrigin(){
        basicDamage = 25;
    }
    public void setCriticalChance(int chance){
        this.chance = chance;
    }
    @Override
    public void setWitchCraftAfterEffect(double i) {
        basicDamage -= (int) ((double) basicDamage * i);
    }
}
