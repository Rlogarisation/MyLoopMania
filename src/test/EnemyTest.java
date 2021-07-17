package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

public class EnemyTest {
    final double initialHp = 5;
    final double initialDamage = 2;
    final double initialMovingSpeed = 1;
    double intialBattleRadius = 1;
    double initialSupportRadius = 1;


    @Test
    public void enemyCreationTest() {
        // Creating current coordinate for enemy.
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<Integer, Integer>(0, 0));
        orderedPath.add(new Pair<Integer, Integer>(0, 1));
        // Creating current world.
        LoopManiaWorld currentWorld = new LoopManiaWorld(10, 10, orderedPath);
        PathPosition slugAPosition = new PathPosition(0, orderedPath);

        Enemy slugA = new Slug(slugAPosition);
        currentWorld.addEnemy(slugA);
        // Check position.
        assertEquals(slugAPosition, slugA.getPathPosition());
        assertEquals(0, slugA.getX());
        assertEquals(0, slugA.getY());

        slugA.moveDownPath();
        assertEquals(0, slugA.getX());
        assertEquals(1, slugA.getY());

        slugA.moveUpPath();
        assertEquals(0, slugA.getX());
        assertEquals(0, slugA.getY());

        // Check hp.
        assertEquals(initialHp, slugA.getHp());

        slugA.setHp(2);
        assertEquals(2, slugA.getHp());

        // Check damage.
        assertEquals(initialDamage, slugA.getDamage());

        slugA.setDamage(3);
        assertEquals(3, slugA.getDamage());

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
        Character character = new Character(slugAPosition);
        currentWorld.setCharacter(character);
        character.setDamage(1);

        // At the end of fight, character win with hp = 4.
        currentWorld.runBattles();

        assertEquals(character.getHp(), 4);
        assertEquals(slugA.getHp(), 0);
        
    }
    
}
