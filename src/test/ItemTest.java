package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
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
    