package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;

public class VampireCastle extends Building{

    private int numCycles;
    private boolean spawnVampire;

    public VampireCastle (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("VampireCastle");
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

    public void buildingEffect(LoopManiaWorld lmw){
        if (this.spawnVampire){
            //spawn vampire
            //createVampire(NearestValidPathPosition(b))
            this.spawnVampire = false;
        }
    }
}
