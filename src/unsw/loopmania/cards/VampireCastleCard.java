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
        //Checks if dropLocation is on the path, if so return false; Must be non-path tile
        //If not on path check if adjacent to path, if adjacent return true, otherwise return false
        for(int i = 0; i<orderedPath.size(); i++){
            if(isAdjacent(orderedPath.get(i), dropLocation) && !isPath(dropLocation, orderedPath)){
                return true;
            }
        }
        return false;
     }
    
 
}
