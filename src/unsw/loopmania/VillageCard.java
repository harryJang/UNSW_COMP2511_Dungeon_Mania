package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a Village card in the backend game world
 */
public class VillageCard extends Card {
    private String villageCardImage;
    public static final String VILLAGECARD = "VILLAGECARD";
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.villageCardImage = "src/images/village_card.png";
    }   

    @Override
    public String getImage() {
        return this.villageCardImage;
    } 

    @Override
    public Village getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Village village = new Village(x, y);
        return village;
    } 
    
    @Override
    public String getName() {
        return VILLAGECARD;
    }
}
