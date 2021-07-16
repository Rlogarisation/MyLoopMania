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

    public void addNewEnemy(Enemy e){
        newEnemies.add(e);
    }

    public void addEnemyKilled(Enemy e){
        enemiesKilled.add(e);
    }
}
