package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

import java.util.List;


public class Village extends Building{
    //needs to be decided
    private int health = 10;

    public Village (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Village");
    }

    public void buildingEffect(LoopManiaWorld lmw){
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
    }

}