package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Barracks building in the world
 */
public class BarracksBuilding extends Building {
    private String barracksBuildingImage;
    public static final String BARRACKS = "BARRACKS";
    public BarracksBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.barracksBuildingImage = "src/images/barracks.png";
    }
    
    @Override
    public String getImage() {
        return this.barracksBuildingImage;
    }

    @Override
    public String getName() {
        return BARRACKS;
    }
}