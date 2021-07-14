package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;


public class ZombiePit extends Building{

    private boolean spawnZombie;

    public ZombiePit (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("ZombiePit");
        this.spawnZombie = false;
    }

    public void incrNumCycles(){
        this.spawnZombie = true;
    }

    public void setSpawnZombie(boolean yesNo){
        this.spawnZombie = yesNo;
    }

    public boolean getSpawnZombie(){
        return this.spawnZombie;
    }

    public void buildingEffect(){
        if (this.spawnZombie == true){
            //spawn zombie
            this.spawnZombie = false;
        }
    }

}
