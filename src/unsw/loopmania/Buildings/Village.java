package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Character;
import java.util.List;


public class Village extends Building{
    //needs to be decided
    private int health = 10;

    public Village (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Village");
    }

    public int getHealth(){
        return this.health;
    }

    public void buildingEffect(Character character){
        if (this.getX() == character.getX() && this.getY() == character.getY()){
            double currentHp = character.getHp();
            currentHp = currentHp + this.getHealth();
            character.setHp(currentHp);
        }
    }

}