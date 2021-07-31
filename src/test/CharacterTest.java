package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

/**
 * the test for character.
 * @author Zheng Luo (z5206267)
 */
public class CharacterTest {
    
    @Test
    public void characterCreationTest() {
        final double initialHp = 100;
        final double initialDamage = 10;
        final double initialMovingSpeed = 2;
        final double initialXp = 0; 
        final double initialGold = 0; 
        final double initialArmour = 0;

        /**
         * Creating current world.
         */
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<Integer, Integer>(0, 0));
        orderedPath.add(new Pair<Integer, Integer>(0, 1));
        orderedPath.add(new Pair<Integer, Integer>(0, 2));
        orderedPath.add(new Pair<Integer, Integer>(1, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 1));
        orderedPath.add(new Pair<Integer, Integer>(1, 0));
        orderedPath.add(new Pair<Integer, Integer>(2, 0));
        LoopManiaWorld currentWorld = new LoopManiaWorld(3, 3, orderedPath);

        // Creating current coordinate for enemy.
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 0));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        Character myHero = new Character(position00);
        currentWorld.setCharacter(myHero);

        // Check position.
        assertEquals(position00, myHero.getPathPosition());
        assertEquals(0, myHero.getX());
        assertEquals(0, myHero.getY());

        myHero.move();
        assertEquals(0, myHero.getX());
        assertEquals(1, myHero.getY());

        // Check hp.
        assertEquals(initialHp, myHero.getHp());

        myHero.setHp(2);
        assertEquals(2, myHero.getHp());

        // Check damage.
        assertEquals(initialDamage, myHero.getDamage());

        myHero.setDamage(3);
        assertEquals(3, myHero.getDamage());

        // Check moving speed.
        assertEquals(initialMovingSpeed, myHero.getMovingSpeed());

        myHero.setMovingSpeed(2);
        assertEquals(2, myHero.getMovingSpeed());

        // Check XP increment.
        assertEquals(initialXp, myHero.getXp());

        myHero.addXp(1);
        assertEquals(1, myHero.getXp());

        // Check gold increment.
        assertEquals(initialGold, myHero.getGold());

        myHero.addGold(1);
        assertEquals(1, myHero.getGold());

        // Check Armour.
        assertEquals(initialArmour, myHero.getTotalArmour());

        myHero.addTotalArmour(1);
        assertEquals(1, myHero.getTotalArmour());

        // Check tower damage if there is a tower in the range.
        myHero.setTowerDamage(1);
        assertEquals(1, myHero.getTowerDamage());

        // Check campfire in range of character.
        assertEquals(false, myHero.getCampfireInRange());
        myHero.setCampfireInRange(true);
        assertEquals(true, myHero.getCampfireInRange());

        // Check fight strategy.
        FightStrategy normalStrategy = new BasicFightStrategy();
        myHero.setFightStrategy(normalStrategy);
        assertEquals(normalStrategy, myHero.getFightStrategy());

        //Check goals system
        assert(myHero.hasAchievedGoal() == false);
        myHero.addXp(100000);
        assert(myHero.hasAchievedGoal() == false);
        myHero.addGold(100000);
        assert(myHero.hasAchievedGoal() == true);
        myHero.setGold(1);
        assert(myHero.hasAchievedGoal() == false);
        myHero.setCycleCount(80);
        assert(myHero.hasAchievedGoal() == true);

    }


    @Test
    /**
     * Test for character stats function
     */
    public void characterStatsTest() {
        final double initialXp = 0; 
        final double initialGold = 0; 

        /**
         * Creating current world.
         */
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<Integer, Integer>(0, 0));
        orderedPath.add(new Pair<Integer, Integer>(0, 1));
        orderedPath.add(new Pair<Integer, Integer>(0, 2));
        orderedPath.add(new Pair<Integer, Integer>(1, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 1));
        orderedPath.add(new Pair<Integer, Integer>(1, 0));
        orderedPath.add(new Pair<Integer, Integer>(2, 0));
        LoopManiaWorld currentWorld = new LoopManiaWorld(3, 3, orderedPath);

        // Creating current coordinate for enemy.
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 0));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        Character myHero = new Character(position00);
        currentWorld.setCharacter(myHero);

        myHero.printCharacterStats();

    }

    @Test
    /**
     * Test for character hasEquipment/wearing equipment functions
     */
    public void characterEquipEquipmentsTest() {
        final double initialXp = 0; 
        final double initialGold = 0; 

        /**
         * Creating current world.
         */
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<Integer, Integer>(0, 0));
        orderedPath.add(new Pair<Integer, Integer>(0, 1));
        orderedPath.add(new Pair<Integer, Integer>(0, 2));
        orderedPath.add(new Pair<Integer, Integer>(1, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 1));
        orderedPath.add(new Pair<Integer, Integer>(1, 0));
        orderedPath.add(new Pair<Integer, Integer>(2, 0));
        LoopManiaWorld d = new LoopManiaWorld(3, 3, orderedPath);

        // Creating current coordinate for enemy.
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 0));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        
        // set the character without equipment
        Character myHero = new Character(position00);
        d.setCharacter(myHero);

        
        Boolean hasHelmet = myHero.getHasHelmet();
        Boolean hasArmour = myHero.getHasArmour();
        Boolean hasShield = myHero.getHasShield();

        // check if the character's hasHelmet, hasArmour and hasShield are false
        assertFalse(hasHelmet);
        assertFalse(hasArmour);
        assertFalse(hasShield);

        // add attack/defense equipments in the unequipped inventory
        d.addUnequippedSword();
        d.addUnequippedArmour();
        d.addUnequippedShield();
        d.addUnequippedHelmet();

        Entity sword = d.getUnequippedInventoryItemEntityByCoordinates(0,0);
        Entity armour = d.getUnequippedInventoryItemEntityByCoordinates(1,0);
        Entity shield = d.getUnequippedInventoryItemEntityByCoordinates(2,0);
        Entity helmet = d.getUnequippedInventoryItemEntityByCoordinates(3,0);

        // set the character to wear attack/defense equipment
        myHero.equipAttackEquipment((AttackEquipment) sword);
        myHero.equipArmour((Armour) armour);
        myHero.equipShield((Shield) shield);
        myHero.equipHelmet((Helmet) helmet);
        
        hasHelmet = myHero.getHasHelmet();
        hasArmour = myHero.getHasArmour();
        hasShield = myHero.getHasShield();

        // check if the character's hasHelmet, hasArmour and hasShield are true
        assertTrue(hasHelmet);
        assertTrue(hasArmour);
        assertTrue(hasShield);
    }

    @Test
    /**
     * Test for character hasEquipment/unequip equipment functions 
     * and character equipment class
     */
    public void characterUnequipEquipmentsTest() {
        final double initialXp = 0; 
        final double initialGold = 0; 

        /**
         * Creating current world.
         */
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<Integer, Integer>(0, 0));
        orderedPath.add(new Pair<Integer, Integer>(0, 1));
        orderedPath.add(new Pair<Integer, Integer>(0, 2));
        orderedPath.add(new Pair<Integer, Integer>(1, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 2));
        orderedPath.add(new Pair<Integer, Integer>(2, 1));
        orderedPath.add(new Pair<Integer, Integer>(1, 0));
        orderedPath.add(new Pair<Integer, Integer>(2, 0));
        LoopManiaWorld d = new LoopManiaWorld(3, 3, orderedPath);

        // Creating current coordinate for enemy.
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 0));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        
        // set the character without equipment
        Character myHero = new Character(position00);
        d.setCharacter(myHero);

        // add attack/defense equipments in the unequipped inventory
        d.addUnequippedSword();
        d.addUnequippedArmour();
        d.addUnequippedShield();
        d.addUnequippedHelmet();

        Entity sword = d.getUnequippedInventoryItemEntityByCoordinates(0,0);
        Entity armour = d.getUnequippedInventoryItemEntityByCoordinates(1,0);
        Entity shield = d.getUnequippedInventoryItemEntityByCoordinates(2,0);
        Entity helmet = d.getUnequippedInventoryItemEntityByCoordinates(3,0);

        // set the character to wear attack/defense equipment
        myHero.equipAttackEquipment((AttackEquipment) sword);
        myHero.equipArmour((Armour) armour);
        myHero.equipShield((Shield) shield);
        myHero.equipHelmet((Helmet) helmet);
        
        Boolean hasHelmet = myHero.getHasHelmet();
        Boolean hasArmour = myHero.getHasArmour();
        Boolean hasShield = myHero.getHasShield();

        // check if the character's hasHelmet, hasArmour and hasShield are true
        assertTrue(hasHelmet);
        assertTrue(hasArmour);
        assertTrue(hasShield);
    
        // set the character to take off attack/defense equipment
        myHero.unequipAttackEquipment();
        myHero.unequipArmour();
        myHero.unequipShield();
        myHero.unequipHelmet();

        hasHelmet = myHero.getHasHelmet();
        hasArmour = myHero.getHasArmour();
        hasShield = myHero.getHasShield();

        // check if the character's hasHelmet, hasArmour and hasShield are true
        assertFalse(hasHelmet);
        assertFalse(hasArmour);
        assertFalse(hasShield);
    
        CharacterEquipment cEquipment = myHero.getCharacterEquipment();

        assertEquals(cEquipment.getAttackEquipment(),null);
        assertEquals(cEquipment.getArmour(),null);
        assertEquals(cEquipment.getShield(),null);
        assertEquals(cEquipment.getHelmet(),null);
        assertEquals(cEquipment.getStump(),null);


    }

    

}
