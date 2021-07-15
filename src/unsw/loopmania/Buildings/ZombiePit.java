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

    public List<Pair<Building, Enemy>> buildingEffect(LoopManiaWorld lmw, List<Pair<Building, Enemy>> trapAndEnemy){
        if (this.spawnZombie == true){
            //spawn zombie
            //createZombie(NearestValidPathPosition(b))
            this.spawnZombie = false;
        }

        return trapAndEnemy;
    }

}
