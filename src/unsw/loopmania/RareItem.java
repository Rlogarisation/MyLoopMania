package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A rare item
 * @author Kihwan Baek
 *
 */
public class RareItem extends StaticEntity {

    private int price;

    /**
     * 3-args constructor 
     * @param x,y is the location of the rare item.
     * @param price is the price of the rare item.
     */
    public RareItem(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setPrice(price);
    }    

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
