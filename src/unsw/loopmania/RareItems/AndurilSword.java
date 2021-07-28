package unsw.loopmania.RareItems;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Sword;

public class AndurilSword extends Sword{
    private final int price = 1500;
    private final int damage = 12;
    public AndurilSword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        this.setPrice(price);
        this.setDamage(damage);
    } 
}
