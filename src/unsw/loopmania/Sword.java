package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A sword
 * @author Kihwan Baek
 *
 */
public class Sword extends AttackEquipment {

    private final int price = 400;
    private final int damage = 6;
    private final int attackRange = 2;

    /**
     * 5-args constructor 
     * @param x,y is the location of the sword.
     * @param price is the price of the sword. (from equipment)
     * @param damage is the damage of the sword.
     * @param attackRange is the attack range of the sword.
     */
    public Sword(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        this.setDamage(damage);
        this.setAttackRange(attackRange);
        /* if (gameMode = berserker) {
            this.setPrice(price*1.5);
        } else {
            this.setPrice(price);
        }
        */
    }    
}
