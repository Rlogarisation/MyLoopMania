package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Character;


public class Barracks extends Building{

    public Barracks (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Barracks");
    }

    public void buildingEffect(Character character){
        if (this.getX() == character.getX() && this.getY() == character.getY()){
            //create new ally
        }
    }

}