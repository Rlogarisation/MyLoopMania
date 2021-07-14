package unsw.loopmania.cards;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.*;

public class CampFireCard extends Card {
    public CampFireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    @Override
    public Campfire toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Campfire campfire = new Campfire(x,y);
        return campfire;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        //Checks if dropLocation is on the path, if so return false; Must be non-path tile
        if(isPath(dropLocation, orderedPath)){
            return false;
        }
        return true;
    }    

    
}
