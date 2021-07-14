package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;

public class TowerCard extends Card{
    public TowerCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }    

    public Tower toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Tower newBuilding = new Tower(x, y);
        return newBuilding;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        //Checks if dropLocation is on the path, if so return false; Must be non-path tile
        if(isPath(dropLocation, orderedPath)){
            return false;
        }
        //If not on path check if adjacent to path, if adjacent return true, otherwise return false
       for(int i = 0; i<orderedPath.size(); i++){
           if(isAdjacent(orderedPath.get(i), dropLocation)){
               return true;
           }
       }
       return false;
    }

}
