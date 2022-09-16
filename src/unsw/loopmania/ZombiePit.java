package unsw.loopmania;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;

/**
 * a Zombie Pit in the world
 */
public class ZombiePit extends Building implements Spawnable{
    private String zombiePitImage;
    public static final String ZOMBIEPIT = "ZOMBIEPIT";

    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.zombiePitImage = "src/images/zombie_pit.png";
    }
    
    @Override
    public Pair<Integer, Integer> spawnEnemy(List<Pair<Integer, Integer>> orderedPath){
        int indexPosition = 0;
        if (orderedPath.contains(Pair.with(getX() + 1, getY() + 1))){
            indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(getX()+ 1, getY()+ 1));
        }else if (orderedPath.contains(Pair.with(getX() - 1, getY() - 1))){
            indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(getX()- 1, getY()- 1));
        }

        if(indexPosition == 0){
            return null;
        }

        Pair<Integer, Integer> pos = orderedPath.get(indexPosition);
        return pos;
    }

    @Override
    public String getImage() {
        return this.zombiePitImage;
    }
    
    @Override
    public String getName() {
        return ZOMBIEPIT;
    }
}
