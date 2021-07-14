package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Character;

public class Barracks extends Building{

    private boolean createAlly;

    public Barracks (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Barracks");
        this.createAlly = false;
    }

    public boolean getCreateAlly(){
        return this.createAlly;
    }

    public void buildingEffect(Character character){

        if (this.getX() == character.getX() && this.getY() == character.getY()){
            this.createAlly = true;
        }
        this.createAlly = false;
    }

}