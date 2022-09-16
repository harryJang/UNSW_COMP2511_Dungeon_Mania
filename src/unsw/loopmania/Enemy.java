package unsw.loopmania;

/**
 * a basic form of enemy in the world
 */
public abstract class Enemy extends MovingEntity {

    private AttackBehaviour attackBehaviour;
    private MoveBehaviour  moveBehaviour;
    private int health;
    private String name;

    public Enemy(PathPosition position) {
        super(position);
    }

    public int getHealth() {
        return health;
    }

    public void loseHealth(int health) {
        this.health = this.health - health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setAttackBehaviour(AttackBehaviour attackBehaviour) {
        this.attackBehaviour = attackBehaviour;
    }

    public void setMoveBehaviour(MoveBehaviour moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    public int getBattleRadius() {
        return attackBehaviour.getBattleRadius();
    }

    public int getSupportRadius() {
        return attackBehaviour.getSupportRadius();
    }


    public void attack(Character character){
        attackBehaviour.attack(character);
    }

    public void move(LoopManiaWorld world){
        moveBehaviour.move(world);
    }

    public abstract String getImage();

    public void setWitchCraftAfterEffect() {
        attackBehaviour.setWitchCraftAfterEffect(1);
    }
}