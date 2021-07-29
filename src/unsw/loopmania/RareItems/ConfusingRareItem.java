package unsw.loopmania.RareItems;

import java.util.Random;


import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;

public class ConfusingRareItem{
    StaticEntity initialRareItem;
    StaticEntity newRareItem;

    public ConfusingRareItem(StaticEntity initialRareItem){
            this.initialRareItem = initialRareItem;
            //Add new random rare item to class
            randomise();
    }


    private StaticEntity randomise(){
        Random prob = new Random();
        SimpleIntegerProperty x = new SimpleIntegerProperty();
        SimpleIntegerProperty y = new SimpleIntegerProperty();
        switch(prob.nextInt(3)){
            case 0:
            this.newRareItem = new AndurilSword(x,y);
            break;
            case 1:
            this.newRareItem = new TreeStump(x,y);
            break;
            case 2:
            this.newRareItem = new TheOneRing(x,y);
            break;
        }
        return newRareItem;
    }

    public StaticEntity getRareItem(){
        return newRareItem;
    }
    
}
