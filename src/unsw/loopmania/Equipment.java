package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;


/**
 * An equipment
 * @author Kihwan Baek
 */
public abstract class Equipment extends StaticEntity {
    
    private int price;

    /**
     * 3-args constructor 
     * @param x,y is the location of the DefenseEquipment.
     * @param price is the price of the DefenseEquipment.
     */
    public Equipment(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
    }

    /**
     * getter for price
     * @return price of the equipment
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * setter for price
     * @param price of the equipment
     */
    public void setPrice(int price) {
        this.price = price;
    }

}
