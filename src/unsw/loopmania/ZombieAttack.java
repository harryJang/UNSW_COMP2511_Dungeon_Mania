package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieAttack implements AttackBehaviour{
    private int battleRadius = 3;
    private int supportRadius = 4;
    private int basicDamage = 15;

    @Override
    public void attack(Character character){
        character.loseHealth(this);
        List<AlliedSoldier> as = character.getAlly();
        List<AlliedSoldier> ToRemove = new ArrayList<AlliedSoldier>();
        Random rand = new Random();
        int chance = rand.nextInt(10);

        for (AlliedSoldier s: as) {
            if (chance == 0 && s.getIfInfected() == false){
                s.setInfection();
                ToRemove.add(s);
            } else {
                s.loseHealth(20);
            }           
            
        }
        if (ToRemove.isEmpty() == false) {
            as.removeAll(ToRemove);
        }
        character.setAlly(as);
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
        basicDamage = 15;
    }
    @Override
    public void setWitchCraftAfterEffect(double i) {
        basicDamage -= (int) ((double) basicDamage * i);
    }
}
