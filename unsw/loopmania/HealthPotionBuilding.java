package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents an health potion on the road in the backend world
 */
public class HealthPotionBuilding extends Building {
    private String healthPotionImage;
    public static final String HEALTHPOTION = "HEALTHPOTION";
    public HealthPotionBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.healthPotionImage = "src/images/brilliant_blue_new.png";
    }    

    @Override
    public String getImage() {
        return this.healthPotionImage;
    }
    
    @Override
    public String getName() {
        return HEALTHPOTION;
    }
}
