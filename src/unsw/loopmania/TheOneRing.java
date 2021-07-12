package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * The one ring
 * @author Kihwan Baek
 *
 */
public class TheOneRing extends RareItem {

    private final int price = 1500;
    /**
     * 3-args constructor 
     * @param x,y is the location of the one ring.
     * @param price is the price of the one ring.
     */
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y, int price) {
        super(x,y);
        this.setPrice(price);
    }    
    
    /*
    // I'll move this function to Character
    public void revival(Character character) {
        character.setHp(100);
    }
    */
}
