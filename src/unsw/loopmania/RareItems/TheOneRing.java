package unsw.loopmania.RareItems;

import org.json.JSONObject;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.HealthPotion;

/**
 * The one ring
 * @author Kihwan Baek
 *
 */
public class TheOneRing extends HealthPotion{
    private final int price = 1500;
    /**
     * 3-args constructor 
     * @param x,y is the location of the one ring.
     * @param price is the price of the one ring.
     */
    public TheOneRing(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setPrice(price);
    }
    
    
}
