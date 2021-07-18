package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A health potion
 * @author Kihwan Baek
 *
 */
public class HealthPotion extends StaticEntity {

    private int price = 200;

    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setPrice(price);
    }    
    
    public int getPrice() {
        return this.price;
    }

    public int setPrice(int price) {
        return this.price = price;
    }
}