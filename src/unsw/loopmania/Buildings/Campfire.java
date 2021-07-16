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
    public void buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        
        //Checking if character is in range
        Character character = lmw.getCharacter();

        if(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2) < this.battleRadius){
            //character.setCampfireInRange(true)
        }
        
        //Checking if vampire is in range
        List<Enemy> enemyList = lmw.getEnemyList();

        for (Enemy e : enemyList){
            if (e instanceof Vampire){
                if(Math.pow((e.getX()-this.getX()), 2) +  Math.pow((e.getY()-this.getY()), 2) < this.battleRadius){
                    //e.setCampfireInRange(true)
                }
            }
        }

    }    

}