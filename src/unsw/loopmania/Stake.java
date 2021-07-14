package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A stake
 * @author Kihwan Baek
 *
 */
public class Stake extends AttackEquipment {

    private final int price = 500;
    private final int damage = 4;
    private final int attackRange = 1;

    /**
     * 5-args constructor 
     * @param x,y is the location of the stake.
     * @param price is the price of the stake. (from equipment)
     * @param damage is the damage of the stake.
     * @param attackRange is the attack range of the stake.
     */
    public Stake(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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
