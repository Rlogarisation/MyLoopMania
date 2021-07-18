package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import org.javatuples.Pair;

import java.util.List;


public class ZombiePit extends Building{

    private boolean spawnZombie;

    public ZombiePit (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        this.spawnZombie = false;
    }

    /**
     * When update is called from Hero's Castle, the ZombiePit will 
     * set the spawnZombie to be true
     */
    public void update(){
        this.spawnZombie = true;
    }

    public boolean getSpawnZombie(){
        return this.spawnZombie;
    }

    /**
     * Check if spawnZombie is true
     * If true, get a valid spawnPosition
     * If there is a valid spawnPosition, create a new zombie
     * Add the zombie to the enemyList and the newEnemies list in newChanges (for frontend)
     * Set the spawnZombie as false
     * SpawnZombie will remain true until a zombie can be spawned on a valid pathPosition
     * Return newChanges - Could include the new zombie in the newEnemies list
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        List<Pair<Integer, Integer>> orderedPath = lmw.getOrderedPath();
        if (this.spawnZombie){
            Pair<Integer, Integer> pos = this.getSpecificSpawnPosition(this, orderedPath, lmw.getEnemyList());
            Enemy newZombie = null;
            if (pos != null){
                int indexInPath = orderedPath.indexOf(pos);
                newZombie = new Zombie(new PathPosition(indexInPath, orderedPath));
                lmw.addEnemy(newZombie);
                newChanges.addNewEnemy(newZombie);
                this.spawnZombie = false;
            }
        }

        return newChanges;
    }

}
