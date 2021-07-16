package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Buildings.*;

/**
 * a Card in the world
 * which doesn't move
 */


public abstract class Card extends StaticEntity {
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    protected boolean isAdjacent(Pair<Integer, Integer> location1, Pair<Integer, Integer> location2){
        if(Math.abs(location1.getValue0()-location2.getValue0()) <= 1 && Math.abs(location1.getValue1()-location2.getValue1()) <= 1){
            return true;
        }
        return false;
    }

    protected boolean isPath(Pair<Integer, Integer> location, List<Pair<Integer, Integer>> orderedPath){
        for (int i=0; i<orderedPath.size(); i++){
            if(location.equals(orderedPath.get(i))){
                return true;
            }
        }
        return false;
    }  
    

    //Returns corresponding building object 
    public abstract Building toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);


    //check if card is in a valid drop
    public abstract boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer,Integer> dropLocation);
}
