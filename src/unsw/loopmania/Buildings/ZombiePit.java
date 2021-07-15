package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;


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

    public void buildingEffect(LoopManiaWorld lmw){
        if (this.spawnZombie == true){
            //spawn zombie
            //createZombie(NearestValidPathPosition(b))
            this.spawnZombie = false;
        }
    }

}
