package unsw.loopmania;

public class AlliedSoldier {

    private int health = 65;
    private int ZombieHealth = 60;
    private boolean ifInfected = false;
    private boolean isTranced = false;
    private boolean becomeEnemy = false;
    private int attackCount = 0;
    private String typeOfAlly = "";
    public AlliedSoldier(){
    }
    public void setAllyType(String type) {
        typeOfAlly = type;
    }
    public String getAllyType() {
        return typeOfAlly;
    }
    public boolean getBecome() {
        return becomeEnemy;
    }

    public void setHealth(int currenthealth){
        this.health = currenthealth;
    }

    public int getHealth() {
        return health;
    }

    public boolean getIfInfected() {
        return ifInfected;
    }

    public void setInfection() {
        ifInfected = true;
    }
    public void setTranced(){
        isTranced = true;
    }

    public void loseHealth(int health) {
        if(ifInfected == false) {
            this.health = this.health - health;
        } else {
            this.ZombieHealth = this.ZombieHealth -health;
        }
    }
    public void backToEnemy() {
        if (this.attackCount > 2) {
            becomeEnemy = true;
        } else {
            becomeEnemy = false;
        }
    }
    public void attack(BasicEnemy e){
        
        if (isTranced) {
            attackCount += 1;
            backToEnemy();
        }
        e.loseHealth(10);
    }
}
