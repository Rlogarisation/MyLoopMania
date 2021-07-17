package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import java.util.Random;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

public class EnemyTest {
    
    @Test
    public void slugCreationTest() {

        final double initialHp = 5;
        final double initialDamage = 2;
        final double initialMovingSpeed = 1;
        double intialBattleRadius = 1;
        double initialSupportRadius = 1;

        
        /**
         * Creating current world.
         * XXX
         * X X
         * XXX
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
        Enemy slugA = new Slug(position00);
        currentWorld.addEnemy(slugA);
        // Check position.
        assertEquals(position00, slugA.getPathPosition());
        assertEquals(0, slugA.getX());
        assertEquals(0, slugA.getY());

        slugA.moveDownPath();
        assertEquals(0, slugA.getX());
        assertEquals(1, slugA.getY());

        slugA.moveUpPath();
        assertEquals(0, slugA.getX());
        assertEquals(0, slugA.getY());

        /**
         * Need function to check random direction movement in Enemy.java
         */
        // Check move function.
        slugA.move();
        
        

        // Check hp.
        assertEquals(initialHp, slugA.getHp());

        slugA.setHp(2);
        assertEquals(2, slugA.getHp());

        // Check damage.
        assertEquals(initialDamage, slugA.getDamage());

        slugA.setDamage(3);
        assertEquals(3, slugA.getDamage());

        // Check moving speed.
        assertEquals(initialMovingSpeed, slugA.getMovingSpeed());

        slugA.setMovingSpeed(2);
        assertEquals(2, slugA.getMovingSpeed());

        // Check support radius and battle radius.
        assertEquals(intialBattleRadius, slugA.getBattleRadius());

        slugA.setBattleRadius(2);
        assertEquals(2, slugA.getBattleRadius());

        assertEquals(initialSupportRadius, slugA.getSupportRadius());
        slugA.setSupportRadius(2);
        assertEquals(2, slugA.getSupportRadius());


        // Check its ability of attack.
        // Slug hp = 2, damage = 3. 
        // character hp = 10, damage = 1.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
        character.setDamage(1);

        // At the end of fight, character win with hp = 4.
        currentWorld.runBattles();

        assertEquals(character.getHp(), 4);
        assertEquals(slugA.getHp(), 0);
        
    }


    
}
