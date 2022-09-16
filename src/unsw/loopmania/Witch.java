package unsw.loopmania;

import java.util.List;
import org.javatuples.Pair;

public class Witch extends Boss {
    private String witchImage;

    public Witch(PathPosition pathPosition){
        super(pathPosition);
        setHealth(200);
        setAttackBehaviour(new WitchAttack());
        setMoveBehaviour(new WitchMove(pathPosition));
        setName("WITCH");
        this.witchImage = "src/images/witch.png";
    }
    @Override
    public String getImage() {
        return this.witchImage;
    }
    public void setEnemyMoveBehaviour(List<BasicEnemy> basicEnemies) {
        for (BasicEnemy basicEnemy : basicEnemies) {
            basicEnemy.setMoveBehaviour(new MoveTowardWitch(basicEnemy.getPathPosition()));
        }
    }

    public void setBackEnemyMoveBehaviour(List<BasicEnemy> basicEnemies, List<Pair<Integer, Integer>> orderedPath) {
        for (BasicEnemy basicEnemy : basicEnemies) {
            basicEnemy.setOriginalMoveBehaviour();
        }
    }
    public void setEnemyAttackBehaviour(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            enemy.setWitchCraftAfterEffect();
        }
    }
}