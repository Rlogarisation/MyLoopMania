package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Enemy;

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


    public void buildingEffect(LoopManiaWorld lmw){
        List<Enemy> enemies = lmw.getEnemyList();

        for (Enemy e : enemies){
            if (this.getX() == e.getX() && this.getY() == e.getY()){
                e.setDamage(e.getDamage() + this.getDamage());
                if (e.getHp() <= 0){
                    lmw.killEnemy(e);
                    //Figure out how to implement killed enemy with LoopManiaWorldController
                }
                //implement removeBuilding(Building b);
                break;
            }
        }
    }

}