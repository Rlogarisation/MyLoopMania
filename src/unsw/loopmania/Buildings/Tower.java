package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import org.javatuples.Pair;

import java.util.List;


public class Tower extends Building{
    //To be decided
    private int battleRadius = 2;
    private int damage = 5;

    public Tower (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Tower");
    }

    public int getBattleRadius(){
        return this.battleRadius;
    }

    public int getDamage(){
        return this.damage;
    }

    public void buildingEffect(LoopManiaWorld lmw, BuildingInfo newChanges){
        Character character = lmw.getCharacter();

        if(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2) < this.getBattleRadius()){
            //character.setTowerDamge(character.getTowerDamage() + this.getDamge())
        }
    }

}