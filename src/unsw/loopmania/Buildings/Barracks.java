package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;


public class Barracks extends Building{

    public Barracks (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
    }

    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){

        Character character = lmw.getCharacter();

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            Ally newAlly = new Ally(character.getPathPosition());
            lmw.addAlly(newAlly);
        }

        return newChanges;
    }

}