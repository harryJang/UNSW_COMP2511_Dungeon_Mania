package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a building in the world
 */
public abstract class Building extends StaticEntity {
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public abstract String getImage();

    @Override
    public abstract String getName();
}