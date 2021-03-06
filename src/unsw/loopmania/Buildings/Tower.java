package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

public class Tower extends Building{
    //To be decided
    private int battleRadius = 3;
    private int damage = 5;

    public Tower (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
    }

    public int getBattleRadius(){
        return this.battleRadius;
    }

    public int getDamage(){
        return this.damage;
    }

    /**
     * Check if the character is in the range
     * If true, add the tower damge to the character's towerDamage
     * Return newChanges - nothing changed
     */
    public BuildingInfo buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        Character character = lmw.getCharacter();

        if(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2) <= Math.pow(this.getBattleRadius(), 2)){
            character.setTowerDamage(character.getTowerDamage() + this.getDamage());
        }

        return newChanges;
    }

}