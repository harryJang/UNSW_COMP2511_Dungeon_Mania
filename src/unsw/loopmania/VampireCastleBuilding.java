package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

import org.javatuples.Pair;

/**
 * a Vampire Castle building in the world
 */
public class VampireCastleBuilding extends Building implements Spawnable{
    private String vampireCastleBuildingImage;
    public static final String VAMPIRECASTLE = "VAMPIRECASTLE";

    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.vampireCastleBuildingImage = "src/images/vampire_castle_building_purple_background.png";
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
        return this.vampireCastleBuildingImage;
    }

    @Override
    public String getName() {
        return VAMPIRECASTLE;
    }
}
