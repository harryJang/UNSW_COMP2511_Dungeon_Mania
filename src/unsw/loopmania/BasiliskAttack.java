package unsw.loopmania;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class BasiliskAttack implements AttackBehaviour{

    private int battleRadius = 5;
    private int supportRadius = 0;
    private int basicDamage = 50;
    private Random venomChance = new Random();
    private int venom = 0;

    @Override
    public void attack (Character character) {
        if (venomChance.nextInt(5) == 0){
            venom = 1;
        }

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
        basicDamage = 50;
        if (venom == 1) {
            venom = 2;
        } else if (venom == 2) {
            venom = 0;
        } 
    }

    public boolean isVenom() {
        if (venom == 0) {
            return false;
        } 
        return true;
    }
    @Override
    public void setWitchCraftAfterEffect(double i) {
        basicDamage -= (int) ((double) basicDamage * i);
    }
}
