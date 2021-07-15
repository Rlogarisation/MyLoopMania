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
        if (this.spawnZombie == true){
            //spawn zombie
            //newChanges.addNewEnemy(createZombie(nearestValidPathPosition(this)));
            this.spawnZombie = false;
        }

    }

}
