package unsw.loopmania;

import java.util.HashMap;
import java.util.Map;

import unsw.loopmania.RareItems.TreeStump;

/**
 * represents the equipments equipped by character in the backend of the game world
 * @author Kihwan Baek
 */
public class CharacterEquipment {
    
    private AttackEquipment attackEquipment;
    private Armour armour;
    private Shield shield;
    private Helmet helmet;
    private TreeStump treeStump;

    public CharacterEquipment() {
        this.equipAttackEquipment(null);
        this.equipArmour(null);
        this.equipShield(null);
        this.equipHelmet(null);
        this.equipTreeStump(null);
    }

    /**
     * equip the character with an attack equipment
     * @return attackEquipment
     */
    public AttackEquipment equipAttackEquipment(AttackEquipment attackEquipment){
        this.attackEquipment = attackEquipment;
        return attackEquipment;
    }

    /**
     * equip the character with an armour
     * @return armour
     */
    public Armour equipArmour(Armour armour){
        this.armour = armour;
        return armour;
    }

    /**
     * equip the character with a shield
     * @return shield
     */
    public Shield equipShield(Shield shield){
        this.shield = shield;
        return shield;
    }

    /**
     * equip the character with a helmet
     * @return helmet
     */
    public Helmet equipHelmet(Helmet helmet){
        this.helmet = helmet;
        return helmet;
    }

    /**
     * equip the character with a helmet
     * @return helmet
     */
    public TreeStump equipTreeStump(TreeStump treeStump){
        this.treeStump = treeStump;
        return treeStump;
    }

    /**
     * unequip the attack equipment for the character
     */
    public void unequipAttackEquipment(){
        this.attackEquipment = null;
    }

    /**
     * unequip the armour for the character
     */
    public void unequipArmour(){
        this.armour = null;
    }

    /**
     * unequip the Shield for the character
     */
    public void unequipShield(){
        this.shield = null;
    }

    /**
     * unequip the helmet for the character
     */
    public void unequipHelmet(){
        this.helmet = null;
    }

    /**
     * unequip the trees tump for the character
     */
    public void unequipTreeStump(){
        this.treeStump = null;
    }
}
