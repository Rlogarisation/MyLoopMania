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

    /**
     * Check if location is adjacent to another location
     * @param location1 pair of x,y 
     * @param location2 pair of x,y
     * @return is adjacent or not
     */
    protected boolean isAdjacent(Pair<Integer, Integer> location1, Pair<Integer, Integer> location2){
        if(Math.abs(location1.getValue0()-location2.getValue0()) <= 1 && Math.abs(location1.getValue1()-location2.getValue1()) <= 1){
            return true;
        }
        return false;
    }

    /**
     * Checks if location is on path
     * @param location location on grid
     * @param orderedPath list of path tiles
     * @return if is on path or not
     */
    protected boolean isPath(Pair<Integer, Integer> location, List<Pair<Integer, Integer>> orderedPath){
        for (int i=0; i<orderedPath.size(); i++){
            if(location.equals(orderedPath.get(i))){
                return true;
            }
        }
        return false;
    }  
    

    /**
     * Converts card to a building to be dropped into world
     * @param x Building X location
     * @param y Building Y location
     * @return New building based on card type
     */
    public abstract Building toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);


    /**
     * Check if valid drop based on card type
     * @param orderedPath refers to path tiles
     * @param dropLocation where the building can be dropped
     * @return
     */
    public abstract boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer,Integer> dropLocation);
}
