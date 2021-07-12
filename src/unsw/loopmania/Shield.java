package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A shield
 * @author Kihwan Baek
 *
 */
public class Shield extends DefenseEquipment {

    private final int price = 500;
    private final Double defense = 0.2;

    /**
     * 4-args constructor 
     * @param x,y is the location of the shield.
     * @param price is the price of the shield (from Equipment).
     * @param defense is the defense of the shield.
     */
    public Shield(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setDefense(defense);
        /* if (gameMode = berserker) {
            this.setPrice(price*1.5);
        } else {
            this.setPrice(price);
        }
        */
    }    
    
}
