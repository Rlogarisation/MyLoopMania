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
    }

    public int getDamage(){
        return this.damage;
    }

    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        List<Enemy> enemies = lmw.getEnemyList();
        for (Enemy enemy : enemies){
            if (this.getX() == enemy.getX() && this.getY() == enemy.getY()){
                enemy.setHp(enemy.getHp() - this.getDamage());
                if (enemy.getHp() <= 0){
                    lmw.killEnemy(enemy);
                    newChanges.addEnemyKilled(enemy);
                }
                lmw.removeBuilding(this);
                break;
            }
        }
        return newChanges;
    }
}