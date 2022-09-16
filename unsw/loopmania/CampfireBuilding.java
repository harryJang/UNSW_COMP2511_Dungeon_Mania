package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Campfire building in the world
 */
public class CampfireBuilding extends Building {
    private String campfireBuildingImage;
    public static final String CAMPFIRE = "CAMPFIRE";
    private int radius = 4;

    public CampfireBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.campfireBuildingImage = "src/images/campfire.png";
    }

    
    @Override
    public String getImage() {
        return this.campfireBuildingImage;
    }

    @Override
    public String getName() {
        return CAMPFIRE;
    }

    public int getRadius() {
        return radius;
    }
}
