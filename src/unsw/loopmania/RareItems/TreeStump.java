package unsw.loopmania.RareItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Shield;



public class TreeStump extends Shield{
    private final int price = 1500;
    private final Double defense = 0.6;
    public TreeStump(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.setPrice(price);
        this.setDefense(defense);
    }
    
}
