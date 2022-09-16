package unsw.loopmania;

/**
 * a basic form of enemy in the world
 */
public abstract class BasicEnemy extends Enemy {

    public BasicEnemy(PathPosition position) {
        super(position);
    }

    public abstract void setOriginalMoveBehaviour();

}