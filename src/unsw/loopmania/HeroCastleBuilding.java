package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Hero Castle building in the world
 */
public class HeroCastleBuilding extends Building {
    private String heroCastleBuildingImage;
    public static final String HEROCASTLE = "HEROCASTLE";
    public HeroCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.heroCastleBuildingImage = "src/images/heros_castle.png";
    }
    
    @Override
    public String getImage() {
        return this.heroCastleBuildingImage;
    }

    @Override
    public String getName() {
        return HEROCASTLE;
    }
}