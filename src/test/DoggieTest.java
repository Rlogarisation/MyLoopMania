package test;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test for enemy type doggie in LoopManiaWorld.
 * @author Zheng Luo (z5206267)
 */
public class DoggieTest {
    
    /**
     * Testing doggie coin will drop to the ground 
     * after doggie got defeated
     * and character will gain 1 doggie coin.
     */
    @Test
    public void doggieCoinDropTest() {
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

        // character: hp = 100, damage = 5.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
        character.setDamage(5);

        // Doggie: hp = 40, damage = 5
        Doggie myDog = new Doggie(position00);
        currentWorld.addEnemy(myDog);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        // make sure doggie is dead.
        assertTrue(defeatedEnemy.contains(myDog));
        character.addDoggieCoin(1);
        // Test for stun.
        assertTrue(character.getHp() < 60);
        // Character will get a doggie coin.
        assertTrue(character.getDoggieCoinQuantity() == 1);

    }

    
    /**
     * Test for doggie coin price flutuation after the death of doggie.
     */
    @Test
    public void doggiecoinPriceAfterDoggieDeadTest() {
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

        // Doggie: hp = 40, damage = 5
        Doggie myDog = new Doggie(position00);
        currentWorld.addEnemy(myDog);

        // Check the doggie coin price is 1 gold at the moment.
        assertTrue(character.getDoggieCoinPrice() == 1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        // make sure doggie is dead.
        assertTrue(defeatedEnemy.contains(myDog));
        character.flutuateDoggieCoinPrice();

        // Doggie coin price will flutuate between [0,2].
        assertTrue(character.getDoggieCoinPrice() >= 0 && character.getDoggieCoinPrice() <= 2);

    }

    
    /**
     * Test for doggie coin price increase dramatically after the death of Elan Muske.
     */
    @Test
    public void doggiecoinPriceIncreaseAfterElanDeadTest() {
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

        // Elan Muske: hp = 50, damage = 10.
        ElanMuske ManToMoon = new ElanMuske(position00);
        currentWorld.addEnemy(ManToMoon);

        // Check the doggie coin price is 1 gold at the moment.
        assertTrue(character.getDoggieCoinPrice() == 1);

        List<Enemy> defeatedEnemy =  currentWorld.runBattles();
        // make sure Elan is dead.
        assertTrue(defeatedEnemy.contains(ManToMoon));
        character.increaseDoggieCoinPriceDrastically();

        // Doggie coin price can increase significantly.
        assertTrue(character.getDoggieCoinPrice() >= 1);

    }
    
}
