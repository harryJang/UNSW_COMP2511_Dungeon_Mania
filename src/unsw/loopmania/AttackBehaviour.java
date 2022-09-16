package unsw.loopmania;

/**
 * Attack Strategy
 */
public interface AttackBehaviour {
    public void attack(Character character);
    public int getBattleRadius();
    public int getSupportRadius();
    public int getDamage();
    public void setDamage(int damage);
    public void setOrigin();
    public void setWitchCraftAfterEffect(double i);
}
