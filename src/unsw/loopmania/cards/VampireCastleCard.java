package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;

/**
 * represents a vampire castle card in the backend game world
 */


public class VampireCastleCard extends Card {
    public VampireCastleCard(SimpleIntegerProperty x, SimpleIntegerProperty y) { 
        super(x, y);
    } 


    public VampireCastle toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        VampireCastle newBuilding = new VampireCastle(x, y);
        return newBuilding;
    }

    

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }
    
 
}
