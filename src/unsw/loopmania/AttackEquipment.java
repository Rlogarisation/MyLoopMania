package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * An attack equipment
 * @author Kihwan Baek
 *
 */
public abstract class AttackEquipment extends Equipment {
    
    private int damage;
    private int attackRange;

    /**
     * 5-args constructor 
     * @param x,y is the location of the AttackEquipment.
     * @param price is the price of the AttackEquipment.
     * @param damage is the damage of the AttackEquipment.
     * @param attackRange is the attack range of the AttackEquipment.
     */
    public AttackEquipment(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
    }

    /**
     * getter for damage
     * @return damage of the attackEquipment
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * getter for attack range
     * @return attack range of the attackEquipment
     */
    public int getAttackRange() {
        return this.attackRange;
    }

    /**
     * setter for damage
     * @param damage of the attackEquipment
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * setter for attack range
     * @param attack range of the attackEquipment
     */
    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

}
