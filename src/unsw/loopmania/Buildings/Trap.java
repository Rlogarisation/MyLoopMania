package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;


public class Trap extends Building{
    //To be decided
    private int damage = 15;

    public Trap (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Trap");
    }

    public int getDamage(){
        return this.damage;
    }

}