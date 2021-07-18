package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

/**
 * this test file tests all the basic equipments(Attack,Defense),
 * potion and 'the one ring'.
 * 
 */
public class ItemTest {
    
    /**
     * This test checks if we can add items when the unequipped inventory is full.
     * and get bonus gold and xp.
     */
    @Test
    public void testFullUnequippedInventory() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);

        int unequippedInventoryWidth = 4;
        int unequippedInventoryHeight = 4;

        for (int x=0; x < unequippedInventoryHeight; x++) {
            for (int y=0; y < unequippedInventoryWidth; y++) {
                d.addUnequippedHealthPotion();
            }    
        }

        d.addUnequippedSword();
        d.addUnequippedStaff();
        d.addUnequippedStake();
        d.addUnequippedArmour();
        d.addUnequippedShield();
        d.addUnequippedHelmet();
        d.addUnequippedHealthPotion();
        d.addUnequippedTheOneRing();

        Entity item = d.getUnequippedInventoryItemEntityByCoordinates(0,0);
        Boolean itemClassSame = (item instanceof Sword);
        assertTrue(itemClassSame);        

        item = d.getUnequippedInventoryItemEntityByCoordinates(1,0);
        itemClassSame = (item instanceof Staff);
        assertTrue(itemClassSame);

        item = d.getUnequippedInventoryItemEntityByCoordinates(2,0);
        itemClassSame = (item instanceof Stake);
        assertTrue(itemClassSame);

        item = d.getUnequippedInventoryItemEntityByCoordinates(3,0);
        itemClassSame = (item instanceof Armour);
        assertTrue(itemClassSame);

        item = d.getUnequippedInventoryItemEntityByCoordinates(0,1);
        itemClassSame = (item instanceof Shield);
        assertTrue(itemClassSame);

        item = d.getUnequippedInventoryItemEntityByCoordinates(1,1);
        itemClassSame = (item instanceof Helmet);
        assertTrue(itemClassSame);

        item = d.getUnequippedInventoryItemEntityByCoordinates(3,1);
        itemClassSame = (item instanceof TheOneRing);
        assertTrue(itemClassSame);

        Double characterGold = c.getGold();
        assertEquals(characterGold, 400);

    }    

    /**
     * This test checks if 'equipOneItem' function add attack equipments
     * in the equipped inventory correctly.
     * The original equipment will be removed from the inventory.  
     */
    @Test
    public void testEquipOneItemAttackWeapon() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);

        int x = 0;
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);
        d.equipOneItem(new Sword(new SimpleIntegerProperty(x), y));

        Entity item = d.getEquippedInventoryItemEntityByCoordinates(0,0);
        Boolean itemClassSame = (item instanceof Sword);
        assertTrue(itemClassSame);
        
        d.equipOneItem(new Staff(new SimpleIntegerProperty(x+1), y));

        item = d.getEquippedInventoryItemEntityByCoordinates(0,0);
        itemClassSame = (item == null);
        assertTrue(itemClassSame);

        item = d.getEquippedInventoryItemEntityByCoordinates(1,0);
        itemClassSame = (item instanceof Staff);
        assertTrue(itemClassSame);
    }

    /**
     * This test checks if 'equipOneItem' function add defense equipments
     * in the equipped inventory correctly.
     * The original equipment will be removed from the inventory.  
     */
    @Test
    public void testEquipOneItemDefenseWeapon() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        d.setCharacter(new Character(new PathPosition(0,orderedPath)));
        Character c = d.getCharacter();

        int x = 0;
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);
        d.addUnequippedArmour();
        d.equipOneItem(new Armour(new SimpleIntegerProperty(x), y));
    
        Entity item = d.getEquippedInventoryItemEntityByCoordinates(0,0);
        Boolean itemClassSame = (item instanceof Armour);
        assertTrue(itemClassSame);
        assertTrue(c.getHasArmour());

        d.addUnequippedArmour();
        d.equipOneItem(new Armour(new SimpleIntegerProperty(x+1), y));

        item = d.getEquippedInventoryItemEntityByCoordinates(1,0);
        assertEquals(null, item);

        d.addUnequippedShield();
        d.equipOneItem(new Shield(new SimpleIntegerProperty(x+1), y));
        
        item = d.getEquippedInventoryItemEntityByCoordinates(1,0);
        itemClassSame = (item instanceof Shield);
        assertTrue(itemClassSame);
        assertTrue(c.getHasShield());

        d.addUnequippedShield();
        d.equipOneItem(new Shield(new SimpleIntegerProperty(x+2), y));
        
        item = d.getEquippedInventoryItemEntityByCoordinates(2,0);
        assertEquals(null, item);
        
        d.addUnequippedHelmet();
        d.equipOneItem(new Helmet(new SimpleIntegerProperty(x+2), y));

        item = d.getEquippedInventoryItemEntityByCoordinates(2,0);
        itemClassSame = (item instanceof Helmet);
        assertTrue(itemClassSame);
        assertTrue(c.getHasHelmet());

        d.addUnequippedShield();
        d.equipOneItem(new Helmet(new SimpleIntegerProperty(x+3), y));
        
        item = d.getEquippedInventoryItemEntityByCoordinates(3,0);
        assertEquals(null, item);
        assertTrue(itemClassSame);

    }

    /**
     * This test checks if 'equipOneItem' function add proper strategy 
     * to the character
     */
    @Test
    public void testEquipItemStrategy() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);

        int x = 0;
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);
        d.equipOneItem(new Sword(new SimpleIntegerProperty(x), y));

        FightStrategy strategy = c.getFightStrategy();
        System.out.println(strategy);
        assertTrue(strategy instanceof SwordStrategy);

        d.equipOneItem(new Staff(new SimpleIntegerProperty(x), y));

        strategy = c.getFightStrategy();
        assertTrue(strategy instanceof StaffStrategy);

        d.equipOneItem(new Stake(new SimpleIntegerProperty(x), y));

        strategy = c.getFightStrategy();
        assertTrue(strategy instanceof StakeStrategy);

    }

    /**
     * This test checks if the 'refillCharacterHealth' function restores 
     * the character's hp when there is no potion in the unequipped inventory.
     */
    @Test
    public void testRefillHealthWithNoPotion() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);

        c.setHp(50);

        d.refillCharacterHealth();

        Double currentHp = c.getHp();
        assertEquals(currentHp,50);

    }

    /**
     * This test checks if the health potion actually restores. 
     * the character's hp when it has low hp.
     */
    @Test
    public void testRefillHealth() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);
        
        d.addUnequippedHealthPotion();
        d.addUnequippedHealthPotion();
        d.addUnequippedHealthPotion();
        c.setHp(50);

        d.refillCharacterHealth();
        
        Double currentHp = c.getHp();
        assertEquals(currentHp,80);

        d.refillCharacterHealth();

        currentHp = c.getHp();
        assertEquals(currentHp,100);

        d.refillCharacterHealth();

        currentHp = c.getHp();
        assertEquals(currentHp,100);

    }

    /**
     * This test checks if the 'reviveCharacter' function revive 
     * the character when there is no 'the one ring' in the unequipped inventory.
     */
    @Test
    public void testReviveWithNoRing() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);

        c.setHp(0);

        d.reviveCharacter();

        Double currentHp = c.getHp();
        assertEquals(currentHp,0);

    }

    /**
     * This test checks if 'the one ring' actually revives. 
     * the character when it is dead.
     */
    @Test
    public void testReviveCharacter() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);

        d.addUnequippedTheOneRing();
        c.setHp(0);

        d.reviveCharacter();

        Double currentHp = c.getHp();
        
        assertEquals(currentHp,100);

    }

}    
    