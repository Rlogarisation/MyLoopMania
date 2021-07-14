package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Character;


public class Campfire extends Building{
    //needs to be decided
    private int battleRadius = 1;

    public Campfire (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Campfire");
    }

    public int getBattleRadius(){
        return this.battleRadius;
    }

    //decide to do range check for character here or in run battles
    public void buildingEffect(Character character){
        if(Math.pow((character.getX()-this.getX()), 2) +  Math.pow((character.getY()-this.getY()), 2) < this.getBattleRadius()){
            //character.setCampfireInRange(true)
        }
    }

}