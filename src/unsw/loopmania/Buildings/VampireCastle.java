package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class VampireCastle extends Building{

    private int numCycles;

    public VampireCastle (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("VampireCastle");
        this.numCycles = 0;
    }

    public int getNumCycles(){
        return this.numCycles;
    }

    public void incrNumCycles(){
        this.numCycles = this.numCycles++;
        if (this.numCycles == 5){
            //execute spawn vampire
            this.numCycles = 0;
        }
    }
}
