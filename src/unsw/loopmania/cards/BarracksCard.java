package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;

public class BarracksCard extends Card {
    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Barracks toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Barracks barracks = new Barracks(x, y);
        return barracks;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        if(isPath(dropLocation, orderedPath)){
            return true;
        }
        return false;
    }  

    
}
