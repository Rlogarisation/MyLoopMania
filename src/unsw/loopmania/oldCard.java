package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a Card in the world
 * which doesn't move
 */
public abstract class oldCard extends StaticEntity {
    // TODO = implement other varieties of card than VampireCastleCard
    public oldCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }
}
