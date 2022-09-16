package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * represents a Vampire castle card in the backend game world
 */
public class VampireCastleCard extends Card {
    private String vampireCastleCardImage;
    public static final String VAMPIRECASTLECARD = "VAMPIRECASTLECARD";
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.vampireCastleCardImage = "src/images/vampire_castle_card.png";
    }   

    @Override
    public String getImage() {
        return this.vampireCastleCardImage;
    } 

    @Override
    public VampireCastleBuilding getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        VampireCastleBuilding vampireCastle = new VampireCastleBuilding(x, y);
        return vampireCastle;
    } 
    
    @Override
    public String getName() {
        return VAMPIRECASTLECARD;
    }
}
