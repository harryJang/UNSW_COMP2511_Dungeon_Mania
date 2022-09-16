package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class ProtectiveGear extends StaticEntity{
    public ProtectiveGear(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public abstract String getImage();

    @Override
    public abstract String getName();

    public abstract void defend(AttackBehaviour attack);
}