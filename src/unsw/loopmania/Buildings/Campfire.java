package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import org.javatuples.Pair;

import java.util.List;


public class Campfire extends Building{
    //needs to be decided
    private int battleRadius = 1;

    public Campfire (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Campfire");
    }

    //decide to do range check for character here or in run battles
    public List<Pair<Building, Enemy>> buildingEffect(LoopManiaWorld lmw, List<Pair<Building, Enemy>> trapAndEnemy){
        Character character = lmw.getCharacter();

        if(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2) < this.battleRadius){
            //character.setCampfireInRange(true)
        }      
        
        return trapAndEnemy;

    }    

}