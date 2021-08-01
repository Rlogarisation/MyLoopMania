package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Enemy;

import java.util.List;


public class Trap extends Building{
    //To be decided
    private int damage = 5;

    public Trap (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
    }

    public int getDamage(){
        return this.damage;
    }

    /**
     * Checking if there are enemies that have the same positon
     * Deal damage to that enemy
     * Check if the enemy's Hp is <= to 0 to apply the killEnemy method
     * Add the killedEnemy to the enemiesKilled list in newChanges for the frontend
     * If the trap deals damage, it has to be removed using the removeBuilding method
     * Return newChanges - Could contain the new enemy killed and the destroyed trap
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        List<Enemy> enemies = lmw.getEnemyList();
        for (Enemy enemy : enemies){
            if (this.getX() == enemy.getX() && this.getY() == enemy.getY()){
                enemy.setHp(enemy.getHp() - this.getDamage());
                if (enemy.getHp() <= 0){
                    lmw.killEnemy(enemy);
                    newChanges.addEnemyKilled(enemy);
                    if (newChanges.getNewEmeies().contains(enemy)){
                        newChanges.getNewEmeies().remove(enemy);
                    }
                }
                lmw.removeBuilding(this);
                break;
            }
        }
        return newChanges;
    }
}