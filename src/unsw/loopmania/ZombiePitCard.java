package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * represents a Zombie Pit card in the backend game world
 */
public class ZombiePitCard extends Card {
    private String zombiePitCardImage;
    public static final String ZOMBIEPITCARD = "ZOMBIEPITCARD";
    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.zombiePitCardImage = "src/images/zombie_pit_card.png";
    }   

    @Override
    public String getImage() {
        return this.zombiePitCardImage;
    } 

    @Override
    public ZombiePit getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        ZombiePit zombiePit = new ZombiePit(x, y);
        return zombiePit;
    } 
    
    @Override
    public String getName() {
        return ZOMBIEPITCARD;
    }
}
