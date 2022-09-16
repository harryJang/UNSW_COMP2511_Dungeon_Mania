package unsw.loopmania;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a Barracks card in the backend game world
 */
public class BarracksCard extends Card {
    private String barracksCardImage;
    public static final String BARRACKSCARD = "BARRACKSCARD";
    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.barracksCardImage = "src/images/barracks_card.png";
    }   
    
    @Override
    public String getImage() {
        return this.barracksCardImage;
    } 

    @Override
    public BarracksBuilding getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        BarracksBuilding barracks = new BarracksBuilding(x, y);
        return barracks;
    } 

    @Override
    public String getName() {
        return BARRACKSCARD;
    }
}
