package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

public class Village extends Building{

    private int health = 10;

    public Village (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
    }

    /**
     * Check if character has the same position
     * If true, increase the character's health by 10
     * If the new health is over 100, cap it at 100
     * Otherwise set the character's health as the new health
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        Character character = lmw.getCharacter();

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            double newHp = character.getHp() + this.health;
            //If health is capped at 100
            if (newHp < 100){
                character.setHp(newHp);
            } else{
            character.setHp(100);
            }
        }

        return newChanges;
    }

}