package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

import java.util.List;


public class Campfire extends Building{

    private int battleRadius = 1;

    public Campfire (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
    }

    /**
     * Check if the character is in range
     * If true, set the character's campfireInRange as true
     * Check if there are any vampires in range
     * If ture, set the vampire's campfireInRange as true
     * Return newChanges - nothing changed
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        
        //Checking if character is in range
        Character character = lmw.getCharacter();

        if(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2) <= Math.pow(this.battleRadius, 2)){
            character.setCampfireInRange(true);
        }
        
        //Checking if vampire is in range
        List<Enemy> enemyList = lmw.getEnemyList();

        for (Enemy e : enemyList){
            if (e instanceof Vampire){
                if(Math.pow((e.getX()-this.getX()), 2) +  Math.pow((e.getY()-this.getY()), 2) <= Math.pow(this.battleRadius, 2)){
                    ((Vampire)e).setCampfireInRange(true);
                }
            }
        }

        return newChanges;

    }    

}