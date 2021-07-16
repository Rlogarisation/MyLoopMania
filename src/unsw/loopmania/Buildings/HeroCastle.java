package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

import java.util.ArrayList;
import java.util.List;


public class HeroCastle extends StaticEntity{ 

    private List<VampireCastle> vampireCastles;
    private List<ZombiePit> zombiePits;
    private int numCyclesComplete;
    private int numCyclesGoal;

    public HeroCastle (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        this.vampireCastles = new ArrayList<VampireCastle>();
        this.zombiePits = new ArrayList<ZombiePit>();
        this.numCyclesComplete = 0;
        this.numCyclesGoal = 1;
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

    public boolean buildingEffect(LoopManiaWorld lmw){
        Character character = lmw.getCharacter();

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            notifyAllObservers();
            if (this.numCyclesComplete == this.numCyclesGoal){
                this.numCyclesComplete = 0;
                this.numCyclesGoal = this.numCyclesGoal++;
                return true;
            }
            this.numCyclesComplete = this.numCyclesComplete++;
        }

        return false;
    }

}
