package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import org.javatuples.Pair;

import java.util.List;

public class Barracks extends Building{

    private boolean createAlly;

    public Barracks (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Barracks");
    }

    public boolean getCreateAlly(){
        return this.createAlly;
    }

    public void buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){

        Character character = lmw.getCharacter();

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            //addAlly(nearestValidPathPosition(b))
        }
    }

}