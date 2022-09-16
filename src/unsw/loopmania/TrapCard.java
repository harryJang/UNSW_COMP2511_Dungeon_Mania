package unsw.loopmania;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * represents a Trap card in the backend game world
 */
public class TrapCard extends Card {
    private String trapCardImage;
    public static final String TRAPCARD = "TRAPCARD";
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.trapCardImage = "src/images/trap_card.png";
    }  
     
    @Override
    public String getImage() {
        return this.trapCardImage;
    } 

    @Override
    public TrapBuilding getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        TrapBuilding trap = new TrapBuilding(x, y);
        return trap;
    } 
    
    @Override
    public String getName() {
        return TRAPCARD;
    }
}
