package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;


public class Campfire extends Building{
    //needs to be decided
    private int battleRadius = 1;

    public Campfire (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Campfire");
    }

}