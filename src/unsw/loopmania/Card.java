package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class Card extends StaticEntity {
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
    @Override
    public abstract String getImage();

    @Override
    public abstract String getName();

    public abstract Building getBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);
}
