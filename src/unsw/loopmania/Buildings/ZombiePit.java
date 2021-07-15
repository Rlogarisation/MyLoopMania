package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import org.javatuples.Pair;

import java.util.List;


public class ZombiePit extends Building{

    private boolean spawnZombie;

    public ZombiePit (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("ZombiePit");
        this.spawnZombie = false;
    }

    public void update(){
        this.spawnZombie = true;
    }

    public boolean getSpawnZombie(){
        return this.spawnZombie;
    }

    public void setSpawnZombie(boolean yesNo){
        this.spawnZombie = yesNo;
    }

    public void buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        List<Pair<Integer, Integer>> orderedPath = lmw.getOrderedPath();
        if (this.spawnZombie){
            Pair<Integer, Integer> pos = this.getSpecificSpawnPosition(this, orderedPath, lmw.getEnemyList());
            Enemy newZombie = null;
            if (pos != null){
                int indexInPath = orderedPath.indexOf(pos);
                newZombie = new Zombie(new PathPosition(indexInPath, orderedPath));
                lmw.addEnemyToEnemyList(newZombie);
                newChanges.addNewEnemy(newZombie);
                this.spawnZombie = false;
            }
        }
    }

}
