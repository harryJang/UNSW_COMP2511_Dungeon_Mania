package unsw.loopmania;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a Tower card in the backend game world
 */
public class TowerCard extends Card {
    private String towerCardImage;
    public static final String TOWERCARD = "TOWERCARD";
    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.towerCardImage = "src/images/tower_card.png";
    }   
    
    @Override
    public String getImage() {
        return this.towerCardImage;
    } 

    @Override
    public TowerBuilding getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        TowerBuilding tower = new TowerBuilding(x, y);
        return tower;
    } 
    
    @Override
    public String getName() {
        return TOWERCARD;
    }

}

