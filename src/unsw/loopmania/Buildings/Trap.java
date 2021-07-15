package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Enemy;
import org.javatuples.Pair;

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


    public List<Pair<Building, Enemy>> buildingEffect(LoopManiaWorld lmw, List<Pair<Building, Enemy>> trapAndEnemy){
        List<Enemy> enemies = lmw.getEnemyList();
        Pair<Building, Enemy> newTrapAndEnemy = new Pair<Building,Enemy>(this, null);
        for (Enemy enemy : enemies){
            if (this.getX() == enemy.getX() && this.getY() == enemy.getY()){
                enemy.setDamage(enemy.getDamage() + this.getDamage());
                if (enemy.getHp() <= 0){
                    lmw.killEnemy(enemy);
                    newTrapAndEnemy.setAt1(enemy);
                    //Figure out how to implement killed enemy with LoopManiaWorldController
                }
                trapAndEnemy.add(newTrapAndEnemy);
                //implement removeBuilding(Building b);
                break;
            }
        }

        return trapAndEnemy;
    }
}