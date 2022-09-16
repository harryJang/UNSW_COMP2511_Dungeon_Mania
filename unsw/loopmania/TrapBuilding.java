package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Trap building in the world
 */
public class TrapBuilding extends Building {
    private String trapBuildingImage;
    public static final String TRAP = "TRAP";
    public TrapBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.trapBuildingImage = "src/images/trap.png";
    }
    
    @Override
    public String getImage() {
        return this.trapBuildingImage;
    }
    
    @Override
    public String getName() {
        return TRAP;
    }
}

