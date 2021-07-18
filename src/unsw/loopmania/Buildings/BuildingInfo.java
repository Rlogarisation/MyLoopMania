package unsw.loopmania.Buildings;
import unsw.loopmania.*;

import java.util.ArrayList;
import java.util.List;


public class BuildingInfo {
    private List<Enemy> newEnemies;
    private List<Enemy> enemiesKilled;

    public BuildingInfo(){
        this.newEnemies = new ArrayList<Enemy>();
        this.enemiesKilled = new ArrayList<Enemy>();
    }

    public List<Enemy> getNewEmeies(){
        return this.newEnemies;
    }

    public List<Enemy> getEnemiesKilledByTrap(){
        return this.enemiesKilled;
    }

    /**
     * Adding new enemy to the list of newEnemies created
     * @param e to be added to newEnemies list
     */
    public void addNewEnemy(Enemy e){
        newEnemies.add(e);
    }

    /**
     * Adding killed enemy to the list of enemiesKilled
     * @param e to be added to enemiesKilled list
     */
    public void addEnemyKilled(Enemy e){
        enemiesKilled.add(e);
    }
}
