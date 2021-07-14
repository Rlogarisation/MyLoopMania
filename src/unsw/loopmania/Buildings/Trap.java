package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.BasicEnemy;
import java.util.List;


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

    public void buildingEffect(List<BasicEnemy> enemies){
        for (BasicEnemy e : enemies){
            if (this.getX() == e.getX() && this.getY() == e.getY()){
                //deal damage to enemy
                //check if enemy is dead
                //remove trap
                break;
            }
        }
    }

}