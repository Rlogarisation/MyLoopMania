package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

/**
 * BattleTest for runBattle function in LoopManiaWorld.
 * @author Zheng Luo (z5206267)
 */
public class BattleTest {
    
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

        assertTrue(character.getHp() >= 0);
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
        assertTrue(character.getHp() >= 0);
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

        assertTrue(character.getHp() >= 0);
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

        assertTrue(character.getHp() >= 0);
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

        assertTrue(character.getHp() >= 0);
    }




}
