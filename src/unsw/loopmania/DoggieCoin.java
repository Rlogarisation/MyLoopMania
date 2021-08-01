package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A doggie coin
 * @author Kihwan Baek
 *
 */
public class DoggieCoin extends StaticEntity {

    private int price = 0;

    public DoggieCoin(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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