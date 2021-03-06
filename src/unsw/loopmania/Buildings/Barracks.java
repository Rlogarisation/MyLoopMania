package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;


public class Barracks extends Building{

    public Barracks (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
    }

    /**
     * Checking if character has the same position as Barracks
     * If true, create an Ally with the same postion as character
     * Return newChanges - nothing changed
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){

        Character character = lmw.getCharacter();

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            lmw.addAlly(character.getPathPosition());
        }

        return newChanges;
    }

}