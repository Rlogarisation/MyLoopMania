package unsw.loopmania.RareItems;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.AttackEquipment;
import unsw.loopmania.DefenseEquipment;
import unsw.loopmania.StaticEntity;

public class ConfusingRareItem extends StaticEntity{
    StaticEntity initialRareItem;
    StaticEntity newRareItem;
    StaticEntity equipped;

    public ConfusingRareItem(SimpleIntegerProperty x, SimpleIntegerProperty y, StaticEntity initalRareItem){
            super(x,y);
            this.initialRareItem = initalRareItem;
            //Add new random rare item to class
            randomise(x,y);
    }


    private StaticEntity randomise(SimpleIntegerProperty x, SimpleIntegerProperty y){
        Random prob = new Random();
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


    public StaticEntity getInitialRareItem(){
        return this.initialRareItem;
    }

    public StaticEntity getNewRareItem(){
        return this.newRareItem;
    }

    public AttackEquipment getSword(){
        if(initialRareItem instanceof AndurilSword){
            return (AttackEquipment)initialRareItem;
        }
        else if(newRareItem instanceof AndurilSword){
            return (AttackEquipment)newRareItem;
        }
        return null;
    }

    public TreeStump getShield(){
        if(initialRareItem instanceof TreeStump){
            return (TreeStump)initialRareItem;
        }
        else if(newRareItem instanceof TreeStump){
            return (TreeStump)newRareItem;
        }
        return null;
    }

    public Boolean hasOneRing(){
        if(initialRareItem instanceof TheOneRing){
            return true;
        }
        else if(newRareItem instanceof TheOneRing){
            return true;
        }
        return false;
    }
    
}
