package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * An armour
 * @author Kihwan Baek
 *
 */
public class Armour extends DefenseEquipment {

    private final int price = 300;
    private final Double defense = 0.5;

    /**
     * 4-args constructor 
     * @param x,y is the location of the helmet.
     * @param price is the price of the helmet (from Equipment).
     * @param defense is the defense of the helmet.
     */
    public Armour(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setPrice(price);
        this.setDefense(defense);
    }    
}
