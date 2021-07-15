package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

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

    public void attach(VampireCastle building){
        this.vampireCastles.add(building);
    }

    public void attach(ZombiePit building){
        this.zombiePits.add(building);
    }

    public void notifyAllObservers(){
        for (VampireCastle building : vampireCastles){
            building.update();
        }
        for (ZombiePit building : zombiePits){
            building.update();
        }
    }

    public void buildingEffect(LoopManiaWorld lmw){
        Character character = lmw.getCharacter();

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            notifyAllObservers();
            //shop
        }
    }

}
