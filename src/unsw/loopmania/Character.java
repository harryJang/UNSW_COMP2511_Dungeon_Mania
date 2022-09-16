package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * represents the main character in the backend of the game world
 */
public class Character extends MovingEntity implements Subject{
    public static final int FULLHEALTH = 200;
    private int basicDamage = 10;
    private int health;
    private List<AlliedSoldier> ally;
    private Observer observer = null;
    private Weapon weapon;
    private List<ProtectiveGear> equippedProtectiveItems;
    private Boolean cheatMode = false;

    public Character(PathPosition position) {
        super(position);
        this.health = FULLHEALTH;
        ally = new ArrayList<AlliedSoldier>();
        weapon = null;
        equippedProtectiveItems = new ArrayList<ProtectiveGear>();
    }

    /**
     * Add the heal value to current health
     * @param heal
     */
    public void addtoHealth(int heal) {
        if (this.health + heal >= FULLHEALTH) {
            this.health = FULLHEALTH;
        } else {
            this.health = this.health + heal;
        }
        if(observer != null){
            observer.update(this);
        }
    }

    public void setEquipedItems(StaticEntity item) {
        if (item.getName().equals(Sword.SWORD) || item.getName().equals(Stake.STAKE) || item.getName().equals(Staff.STAFF) || item.getName().equals(Anduril.ANDURIL)){
            this.weapon = (Weapon) item;
        } else {
            equippedProtectiveItems.add((ProtectiveGear) item);
        }
    }

    public void removeItem(StaticEntity item) {
        if (item.getName().equals(Sword.SWORD) || item.getName().equals(Stake.STAKE) || item.getName().equals(Staff.STAFF) || item.getName().equals(Anduril.ANDURIL)){
            this.weapon = null;
        } else {
            equippedProtectiveItems.remove(item);
        }
    }

    public List<ProtectiveGear> getEquipedItems() {
        return equippedProtectiveItems;
    }

    public StaticEntity getEquipedWeapon() {
        return weapon;
    }
    
    public void addAlly(AlliedSoldier as) {
        ally.add(as);
    }

    public void setAlly(List<AlliedSoldier> as) {
        this.ally = as;
    }

    public void removeListofAlly(List<AlliedSoldier> dead) {
        ally.removeAll(dead);
    }

    public List<AlliedSoldier> getAlly() {
        return ally;
    }

    public int getSizeofAlly() {
        return ally.size();
    }

    /**
     * Lose health during battle
     * @param health
     */
    public void loseHealth(AttackBehaviour attack) {
        if (cheatMode == false) {
            if(equippedProtectiveItems.isEmpty() == false) {
                if (attack instanceof BasiliskAttack) {
                    BasiliskAttack bAttack = (BasiliskAttack) attack;
                    //isVenom need to return if it is under venom bite
                    //if so, update the venom indicator to the next step
                    if (!bAttack.isVenom()) {
                        for (ProtectiveGear e : equippedProtectiveItems){
                            e.defend(attack);
                        }                    
                    }
                } else {
                    for (ProtectiveGear e : equippedProtectiveItems){
                        e.defend(attack);
                    }
                }
            }
            int health = attack.getDamage();
            this.health = this.health - health;
            attack.setOrigin();
            if(observer != null){
                observer.update(this);
            }
        } else {
            setFullHealth();
        }
    }

    /**
     * Lose given number of health
     * @param health
     */
    public void loseHealth(int reducedValue) {
        this.health -= reducedValue;
    }

    public void setFullHealth() {
        this.health = FULLHEALTH;
        if(observer != null){
            observer.update(this);
        }
    }

    public int getHealth() {
        return health;
    }

    /**
     * Attack Enemies
     * 
     * weapon, armour, shield, helmet
     * @param enemy
     */
    public void BasicAttackEnemy(List<Enemy> enemy, boolean isCampfire){
        int attackDamage = basicDamage;

        for(Enemy e: enemy){
            attackDamage = (int) ((double)attackDamage*(1-((double)checkHelmet()/100.0)));
            if (weapon == null) {
                e.loseHealth(attackDamage);
                if (isCampfire == true) e.loseHealth(attackDamage);
            } else {
                weapon.attackEnemies(e, attackDamage);
                if (isCampfire == true) weapon.attackEnemies(e, attackDamage);
            }

            if (weapon instanceof Staff) {
                Random rand = new Random();
                int trance = rand.nextInt(20);
                if (trance == 1 && enemy.size() == 1) {
                    e.loseHealth(e.getHealth());
                } else if (trance == 1 && enemy.size() > 1) {
                    AlliedSoldier tempmate = new AlliedSoldier();
                    tempmate.setAllyType(e.getName());
                    tempmate.setHealth(e.getHealth());
                    tempmate.setTranced();
                    ally.add(tempmate);
                    e.loseHealth(e.getHealth());
                }
            }
        }
    }

    public Integer checkHelmet() {
        for (StaticEntity item : equippedProtectiveItems) {
            if (item.getName().equals(Helmet.HELMET)) {
                return ((Helmet) item).getDefense();
            }
        }
        return 0;
    }

    public void enableCheat() {
        cheatMode = true;
    }

    public void disableCheat() {
        cheatMode = false;
    }
    
    @Override
    public void notifyObservers(){
        if(observer != null){
            observer.update(this);
        }
    }

    @Override
	public void attach(Observer o) {
		observer = o;
	}

	@Override
	public void detach() {
		observer = null;
	}
}
