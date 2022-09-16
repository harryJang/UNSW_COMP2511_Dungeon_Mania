package unsw.loopmania;

public class Zombie extends BasicEnemy{
    private String zombieImage;

    public Zombie(PathPosition pathPosition){
        super(pathPosition);
        setHealth(60);
        setAttackBehaviour(new ZombieAttack());
        setMoveBehaviour(new ZombieMove(pathPosition));
        setName("ZOMBIE");
        this.zombieImage = "src/images/zombie.png";
    }

    @Override
    public String getImage() {
        return this.zombieImage;
    }

    @Override
    public void setOriginalMoveBehaviour() {
        setMoveBehaviour(new VampireMove(super.getPathPosition()));
    }
}
