package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A helmet
 * @author Kihwan Baek
 *
 */
public class Helmet extends DefenseEquipment {

    private final int price = 600;
    private final Double defense = 0.3;

    /**
     * 4-args constructor 
     * @param x,y is the location of the helmet.
     * @param price is the price of the helmet (from Equipment).
     * @param defense is the defense of the helmet.
     */
    public Helmet(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setDefense(defense);
    }    
   
}
