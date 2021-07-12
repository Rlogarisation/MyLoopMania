package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;


public class Barracks extends Building{

    public Barracks (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Barracks");
    }

}