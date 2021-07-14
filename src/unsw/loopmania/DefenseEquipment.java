package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A defense equipment
 * @author Kihwan Baek
 *
 */
public  abstract class DefenseEquipment extends Equipment {
    
    private Double defense;

    /**
     * 4-args constructor 
     * @param x,y is the location of the DefenseEquipment.
     * @param price is the price of the DefenseEquipment (from Equipment).
     * @param defense is the defense of the DefenseEquipment.
     */
    public DefenseEquipment(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
    }

    /**
     * getter for defense
     * @return defense of the defenseEquipment
     */
    public Double getDefense() {
        return this.defense;
    }

    /**
     * setter for defense
     * @param defense of the defenseEquipment
     */
    public void setDefense(Double defense) {
        this.defense = defense;
    }

}
