package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Buildings.*;

/**
 * BattleTest for runBattle function in LoopManiaWorld.
 * @author Zheng Luo (z5206267)
 */
public class BattleTest {

    @Test
    /**
     * Test the loopManiaWorld can be generated as expected.
     */
    public void worldCreationTest() {
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

        assertEquals(3, currentWorld.getWidth());
        assertEquals(3, currentWorld.getHeight());
    }

    @Test
    /**
     * Test the stability of the program if there is no enemy, but ally and character.
     */
    public void noEnemyTest() {
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


        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // ally: hp = 20, damage = 5
        Ally ally1 = new Ally(position00);
        currentWorld.addAlly(ally1);


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        assertTrue(currentWorld.getCharacterIsAlive());
        assertTrue(character.getHp() > 0 && ally1.getHp() > 0);
        assertTrue(defeatedEnemy.size() == 0);
    }

    @Test
    /**
     * Test the stability of the program if there is no enemy, no ally but character.
     */
    public void noEnemyorAllyTest() {
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


        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);



        List<Enemy> defeatedEnemy =  currentWorld.runBattles();

        assertTrue(currentWorld.getCharacterIsAlive());
        assertTrue(character.getHp() == 100);
        assertTrue(defeatedEnemy.size() == 0);
    }
    
    @Test
    /**
     * all enemies and character are on the same position, no ally in this test yet.
     * test fight battle capability.
     */
    public void basicBattleTest() {
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

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // slug: hp = 5 and damage = 2.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        Slug slug2 = new Slug(position00);
        currentWorld.addEnemy(slug2);

        // vampire: hp = 10 and damage = 10, have chance to deal extra damage.
        Vampire vampire1 = new Vampire(position00);
        currentWorld.addEnemy(vampire1);
        // zombie: hp = 5 and damage = 5, havee chance to tranform ally into zombie.
        Zombie zombie1 = new Zombie(position00);
        currentWorld.addEnemy(zombie1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(defeatedEnemy.contains(slug2));
        assertTrue(defeatedEnemy.contains(vampire1));
        assertTrue(defeatedEnemy.contains(zombie1));

        assertTrue(character.getHp() > 0);
        assertTrue(currentWorld.getCharacterIsAlive());
    }


    @Test
    /**
     * all enemies, ally and character are on the same position, 
     * to test ally will fight with all enemy first.
     */
    public void basicAllyBattleTest() {
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

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // ally: hp = 20, damage = 5
        Ally ally1 = new Ally(position00);
        currentWorld.addAlly(ally1);

        
        // slug: hp = 5 and damage = 2.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        Slug slug2 = new Slug(position00);
        currentWorld.addEnemy(slug2);

        // vampire: hp = 10 and damage = 10, have chance to deal extra damage.
        Vampire vampire1 = new Vampire(position00);
        currentWorld.addEnemy(vampire1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(defeatedEnemy.contains(slug2));
        assertTrue(defeatedEnemy.contains(vampire1));

        assertTrue(ally1.getHp() <= 0);
        assertTrue(character.getHp() > 0);
        assertTrue(currentWorld.getCharacterIsAlive());
    }

    @Test
    /**
     * contain enemies and character to test the battle radius.
     */
    public void basicBattleRadiusTest() {
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

        int index01InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 1));
        PathPosition position01 = new PathPosition(index01InPath, orderedPath);
        
        int index02InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 2));
        PathPosition position02 = new PathPosition(index02InPath, orderedPath);

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // slug: hp = 5 and damage = 2, radius = 1.
        Slug slug1 = new Slug(position01);
        currentWorld.addEnemy(slug1);

        // vampire: hp = 10 and damage = 10, have chance to deal extra damage, radius = 3.
        Vampire vampire1 = new Vampire(position02);
        currentWorld.addEnemy(vampire1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(defeatedEnemy.contains(vampire1));

        assertTrue(character.getHp() > 0);
        assertTrue(currentWorld.getCharacterIsAlive());
    }

    @Test
    /**
     * contain enemies and character to test the support radius.
     */
    public void basicSupportRadiusTest() {
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

        int index10InPath = orderedPath.indexOf(new Pair<Integer, Integer>(1, 0));
        PathPosition position10 = new PathPosition(index10InPath, orderedPath);
        
        int index02InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 2));
        PathPosition position02 = new PathPosition(index02InPath, orderedPath);

        // character: hp = 100, damage = 10.
        Character character = new Character(position10);
        currentWorld.setCharacter(character);

        // slug: hp = 5 and damage = 2, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);

        // vampire: hp = 10 and damage = 10, have chance to deal extra damage, radius = 3.
        Vampire vampire1 = new Vampire(position02);
        vampire1.setBattleRadius(2);
        vampire1.setSupportRadius(2);
        currentWorld.addEnemy(vampire1);

        // At the moment vampire is not in fighting range of character.
        assertTrue(!currentWorld.withinRange(character, vampire1, vampire1.getBattleRadius()));

        // But they still fighted due to slug in the support radius of vampire.
        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(defeatedEnemy.contains(vampire1));

        assertTrue(character.getHp() > 0);
        assertTrue(currentWorld.getCharacterIsAlive());
    }

    @Test
    /**
     * Test zombie's ability to transform a ally into zombie, and fight for enemy.
     */
    public void zombieAllyTransformationTest() {
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


        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // zombie: hp = 5 and damage = 5, havee chance to tranform ally into zombie.
        Zombie zombie1 = new Zombie(position00);
        currentWorld.addEnemy(zombie1);
        zombie1.setHp(40);

        // ally: hp = 20, damage = 5
        Ally ally1 = new Ally(position00);
        currentWorld.addAlly(ally1);
        ally1.setHp(40);


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        // If there is 2 enemies died at the end, means ally has been transformed into zombie.
        assertTrue(defeatedEnemy.size() == 2);

        assertTrue(character.getHp() > 0);
        assertTrue(currentWorld.getCharacterIsAlive());
    }


    @Test
    /**
     * Test for the creation of tranced ally.
     * Test for the situation of tranced ally got killed within 2 attacks.
     */
    public void trancedAllyGotKilledTest() {
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


        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // zombie: hp = 5 and damage = 5, have chance to tranform ally into zombie.
        // It got transformed into tranced ally with hp = 20, damage = 5.
        Zombie zombie1 = new Zombie(position00);
        currentWorld.addEnemy(zombie1);
        zombie1.setIsTranced(true);
    
        // slug: hp = 10 and damage = 20, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        slug1.setHp(10);
        slug1.setDamage(20);

        // trancedAlly: hp = 0, slug hp = 5.
        // character hp = 80, slug hp = -5.


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();


        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(defeatedEnemy.size() == 1);

        assertTrue(currentWorld.getAllyList().isEmpty());
        assertEquals(-5, slug1.getHp());
        assertEquals(80, character.getHp());
        assertTrue(currentWorld.getCharacterIsAlive());
    }

    @Test
    /**
     * Test for the creation of tranced ally.
     * Test for the situation of the whole battle finished within 2 attacks for tranced ally,
     * and tranced ally will die as ally in the end of game.
     */
    public void trancedAllyDieTest() {
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


        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // zombie: hp = 5 and damage = 5, have chance to tranform ally into zombie.
        // It got transformed into tranced ally with hp = 20, damage = 5.
        Zombie zombie1 = new Zombie(position00);
        currentWorld.addEnemy(zombie1);
        zombie1.setIsTranced(true);
    
        // slug: hp = 5 and damage = 5, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        slug1.setHp(5);
        slug1.setDamage(5);

        // trancedAlly: hp = 15 (Die), slug hp = 0, character hp = 100.


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();


        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(defeatedEnemy.size() == 1);

        assertTrue(currentWorld.getAllyList().isEmpty());
        assertEquals(0, slug1.getHp());
        assertEquals(100, character.getHp());
        assertTrue(currentWorld.getCharacterIsAlive());
    }


    @Test
    /**
     * Test for the creation of tranced ally.
     * Test for the situation of killed an enemy with two or more attacks,
     * then transform back to its original enemy,
     * at this moment, no ally left, 
     * and transformed enemy will fight with character,
     * and transformed enemy will die in the end.
     */
    public void trancedAllyKillingTest() {
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


        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // zombie: hp = 5 and damage = 5, have chance to tranform ally into zombie.
        // It got transformed into tranced ally with hp = 20, damage = 5.
        Zombie zombie1 = new Zombie(position00);
        currentWorld.addEnemy(zombie1);
        zombie1.setIsTranced(true);
    
        // slug: hp = 10 and damage = 2, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        slug1.setHp(10);

        // trancedAlly will kill slug first with two attacks, met the condition for transformation.
        // trancedAlly hp = 16, slug hp = 0.
        // then transform back to zombie with hp = 5, damage = 5
        // character hp = 95, zombie hp = -5.


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();

        assertTrue(defeatedEnemy.size() == 2);
        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(currentWorld.getAllyList().isEmpty());
        assertEquals(-5, zombie1.getHp());
        assertEquals(95, character.getHp());
        assertTrue(currentWorld.getCharacterIsAlive());
    }



    @Test
    /**
     * Test the campfire property when character is with range.
     */
    public void campfireWithinRangeTest() {
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

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        Building newCampfire = new Campfire(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        currentWorld.addBuildingToBuildingList(newCampfire);
        currentWorld.buildingInteractions();
    
        // slug: hp = 20 and damage = 2, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        slug1.setHp(20);
        slug1.setDamage(2);


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();

        /**
         * The battle will finished within 1 attack, character's damage will double to 20.
         * if character.hp is anything not 98, then it is incorrect.
         */

        assertTrue(defeatedEnemy.contains(slug1));
        assertTrue(currentWorld.getAllyList().isEmpty());
        assertEquals(98, character.getHp());
        assertEquals(0, slug1.getHp());
        assertTrue(currentWorld.getCharacterIsAlive());
    }

    @Test
    /**
     * Test when character is dead.
     */
    public void characterDeadTest() {
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


        // character: hp = 5, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
        character.setHp(5);


        // slug: hp = 20 and damage = 2, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        slug1.setHp(20);
        slug1.setDamage(5);


        List<Enemy> defeatedEnemy =  currentWorld.runBattles();


        assertTrue(defeatedEnemy.isEmpty());
        assertTrue(currentWorld.getAllyList().isEmpty());
        assertEquals(0, character.getHp());
        assertEquals(10, slug1.getHp());
        assertTrue(!currentWorld.getCharacterIsAlive());
    }

    @Test
    /**
     * Test the function of chanceGenerator.
     */
    public void chanceGeneratorTest() {
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

        boolean hasTrue = false;
        boolean hasFalse = false;


        for (int i = 0; i < 10; i++) {
            if (currentWorld.chanceGenerator(0.3)) {
                hasTrue = true;
            }
            else {
                hasFalse = true;
            }
        }
        assertTrue(hasTrue && hasFalse);
        
    }

}
