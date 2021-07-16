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

    public void setSpawnVampire(boolean yesNo){
        this.spawnVampire = yesNo;
    }

    public void update(){
        this.numCycles = this.numCycles++;
        if (this.numCycles == 5){
            this.spawnVampire = true;
            this.numCycles = 0;
        } else{
            this.spawnVampire = false;
        }
    }

    public void buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        List<Pair<Integer, Integer>> orderedPath = lmw.getOrderedPath();
        if (this.spawnVampire){
            Pair<Integer, Integer> pos = this.getSpecificSpawnPosition(this, orderedPath, lmw.getEnemyList());
            Enemy newVampire = null;
            if (pos != null){
                int indexInPath = orderedPath.indexOf(pos);
                newVampire = new Vampire(new PathPosition(indexInPath, orderedPath));
                lmw.addEnemyToEnemyList(newVampire);
                newChanges.addNewEnemy(newVampire);
                this.spawnVampire = false;
            }
        }
    }
}
