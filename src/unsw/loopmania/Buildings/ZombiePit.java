package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;

public class ZombiePit extends Building{

    public ZombiePit (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("ZombiePit");
    }

}
