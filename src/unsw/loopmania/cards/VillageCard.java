package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;

public class VillageCard extends Card {
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    

    public Village toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Village newBuilding = new Village(x, y);
        return newBuilding;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        if(isPath(dropLocation, orderedPath)){
            return true;
        }
        return false;
    }
}
