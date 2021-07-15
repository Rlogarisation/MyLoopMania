package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import org.javatuples.Pair;

import java.util.List;

/**
 * a basic form of building in the world
 */
public abstract class Building extends StaticEntity {
    // add more types of building, and make sure buildings have effects on entities as required by the spec
    private String type;

    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public abstract void buildingEffect(LoopManiaWorld lmw, List<Pair<Building, Enemy>> trapAndEnemy);
}
