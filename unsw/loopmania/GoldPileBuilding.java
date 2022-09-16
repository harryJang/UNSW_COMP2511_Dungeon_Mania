package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a gold pile on the road in the backend world
 */
public class GoldPileBuilding extends Building {
    private String goldImage;
    public static final String GOLDPILE = "GOLDPILE";
    public GoldPileBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.goldImage = "src/images/gold_pile.png";
    }    

    @Override
    public String getImage() {
        return this.goldImage;
    }
    
    @Override
    public String getName() {
        return GOLDPILE;
    }
}
