package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import java.util.ArrayList;
import java.util.List;


public class HeroCastle extends Building{

    private List<VampireCastle> vampireCastles;
    private List<ZombiePit> zombiePits;

    public HeroCastle (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("HeroCastle");
        this.vampireCastles = new ArrayList<VampireCastle>();
        this.zombiePits = new ArrayList<ZombiePit>();
    }

    public void addVampireCastle(VampireCastle building){
        this.vampireCastles.add(building);
    }

    public void addZombiePit(ZombiePit building){
        this.zombiePits.add(building);
    }

    public void updateObservers(){
        for (VampireCastle building : vampireCastles){
            building.incrNumCycles();
        }
        for (ZombiePit building : zombiePits){
            building.incrNumCycles();
        }
    }

}
