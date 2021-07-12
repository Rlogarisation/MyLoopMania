package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;


public class Tower extends Building{
    //To be decided
    private int battleRadius = 2;
    private int damage = 5;

    public Tower (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Tower");
    }

}