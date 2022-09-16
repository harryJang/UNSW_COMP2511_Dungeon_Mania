package unsw.loopmania;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a Campfire card in the backend game world
 */
public class CampfireCard extends Card {
    private String campfireCardImage;
    public static final String CAMPFIRECARD = "CAMPFIRECARD";

    public CampfireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.campfireCardImage = "src/images/campfire_card.png";
    }   
    
    @Override
    public String getImage() {
        return this.campfireCardImage;
    } 

    @Override
    public CampfireBuilding getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        CampfireBuilding campfire = new CampfireBuilding(x, y);
        return campfire;
    } 
    
    @Override
    public String getName() {
        return CAMPFIRECARD;
    }
}
