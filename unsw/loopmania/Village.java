package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Village building in the world
 */
public class Village extends Building {
    private String villageImage;
    public static final String VILLAGE = "VILLAGE";
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.villageImage = "src/images/village.png";
    }
    
    @Override
    public String getImage() {
        return this.villageImage;
    }
    
    @Override
    public String getName() {
        return VILLAGE;
    }
}

