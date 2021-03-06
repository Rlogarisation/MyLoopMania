package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;

public class TrapCard extends Card{
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }   
    
    public Trap toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Trap newBuilding = new Trap(x, y);
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
