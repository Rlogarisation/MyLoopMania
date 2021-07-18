package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A staff
 * @author Kihwan Baek
 *
 */
public class Staff extends AttackEquipment {

    private final int price = 500;
    private final int damage = 3;

    /**
     * 5-args constructor 
     * @param x,y is the location of the staff.
     * @param price is the price of the staff. (from equipment)
     * @param damage is the damage of the staff.
     * @param attackRange is the attack range of the staff.
     */
    public Staff(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setPrice(price);
        this.setDamage(damage);
    }    
}
