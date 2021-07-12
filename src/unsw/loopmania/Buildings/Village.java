package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;


public class Village extends Building{
    //needs to be decided
    private int health = 10;

    public Village (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Village");
    }

}