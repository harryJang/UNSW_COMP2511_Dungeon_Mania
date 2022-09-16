package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public abstract class Weapon extends StaticEntity{
    public Weapon(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public abstract String getImage();

    @Override
    public abstract String getName();

    public abstract void attackEnemies(Enemy enemies, int actualDamage);
}