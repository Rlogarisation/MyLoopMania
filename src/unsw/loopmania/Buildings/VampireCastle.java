package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import org.javatuples.Pair;

import java.util.List;

public class VampireCastle extends Building{

    private int numCycles;
    private boolean spawnVampire;

    public VampireCastle (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        this.numCycles = 0;
        this.spawnVampire = false;
    }

    public boolean getSpawnVampire(){
        return this.spawnVampire;
    }

    //Helper method for ease in testing to bypass the 5 complete cycles
    public void setSpawnVampire(boolean yesNo){
        this.spawnVampire = yesNo;
    }

    /**
     * When update is called from Hero's Castle, the VampireCastle will 
     * increment the numCycles
     * If the numCycles is equal to 5, the spawnVampire is set as true
     */
    public void update(){
        this.numCycles = this.numCycles + 1;
        if (this.numCycles == 5){
            this.spawnVampire = true;
            this.numCycles = 0;
        } else{
            this.spawnVampire = false;
        }
    }

    /**
     * Check if spawnVampire is true
     * If true, get a valid spawnPosition
     * If there is a valid spawnPosition, create a new vampire
     * Add the vampire to the enemyList and the newEnemies list in newChanges (for frontend)
     * Set the spawnVampire as false
     * SpawnVampire will remain true until a vampire can be spawned on a valid pathPosition
     * Return newChanges - Could include the new vampire in the newEnemies list
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        List<Pair<Integer, Integer>> orderedPath = lmw.getOrderedPath();
        if (this.spawnVampire){
            Pair<Integer, Integer> pos = this.getSpecificSpawnPosition(this, orderedPath, lmw.getEnemyList());
            Enemy newVampire = null;
            if (pos != null){
                int indexInPath = orderedPath.indexOf(pos);
                newVampire = new Vampire(new PathPosition(indexInPath, orderedPath));
                lmw.addEnemy(newVampire);
                newChanges.addNewEnemy(newVampire);
                this.spawnVampire = false;
            }
        }

        return newChanges;
    }
}
