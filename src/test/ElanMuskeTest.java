package test;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test for enemy type Elan Muske in LoopManiaWorld.
 * @author Zheng Luo (z5206267)
 */
public class ElanMuskeTest {

    /**
     * Test the ability of ElanMusky to heal itself in the battle.
     */
    @Test
    public void healingItselfTest() {
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

        // character: hp = 100, damage = 1.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
        character.setDamage(1);

        // Elan Muske: hp = 50, damage = 1.
        ElanMuske ManToMoon = new ElanMuske(position00);
        currentWorld.addEnemy(ManToMoon);
        ManToMoon.setDamage(1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        // make sure Elan is dead.
        assertTrue(defeatedEnemy.contains(ManToMoon));
        
        // orginally Elan will be defeated when character has hp = 50 left,
        // However, if Elan heals itself, character will end up less than 50.
        assertTrue(character.getHp() < 50);
    }

    /**
     * Test the ability to heal all surrounding enermies in range.
     */
    @Test
    public void healingAllNearbyEnemiesTest() {
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

        // character: hp = 100, damage = 1.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
        character.setDamage(1);

        // Elan Muske: hp = 50, damage = 1.
        ElanMuske ManToMoon = new ElanMuske(position00);
        currentWorld.addEnemy(ManToMoon);
        ManToMoon.setDamage(1);
        // slug: hp = 5 and damage = 2.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        // make sure Elan is dead.
        assertTrue(defeatedEnemy.contains(ManToMoon));
        
        // orginally Elan will be defeated when character has hp = 40 left,
        // However, if Elan heals everyone, character will end up less than 40.
        assertTrue(character.getHp() < 40);
    }
}
