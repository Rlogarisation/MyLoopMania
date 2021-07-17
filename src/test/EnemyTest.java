package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

public class EnemyTest {
    
    @Test
    public void slugCreationTest() {

        final double initialHp = 5;
        final double initialDamage = 2;
        final double initialMovingSpeed = 1;
        double initialBattleRadius = 1;
        double initialSupportRadius = 1;
        double chanceOfEffect = 0.3;

        
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
        Slug slugA = new Slug(position00);
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
        // slugA.move();
        
        

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
        assertEquals(initialBattleRadius, slugA.getBattleRadius());

        slugA.setBattleRadius(2);
        assertEquals(2, slugA.getBattleRadius());

        assertEquals(initialSupportRadius, slugA.getSupportRadius());
        slugA.setSupportRadius(2);
        assertEquals(2, slugA.getSupportRadius());

        // Check chance of effect.
        assertEquals(chanceOfEffect, slugA.getChanceOfEffect());

        slugA.setChanceOfEffect(0.4);
        assertEquals(0.4, slugA.getChanceOfEffect());

        // Check set is tranced.
        assertEquals(false, slugA.getTrancedStatus());
        
        slugA.setIsTranced(true);
        assertEquals(true, slugA.getTrancedStatus());
        slugA.setIsTranced(false);

        // Check fight strategy.
        FightStrategy normalStrategy = new BasicFightStrategy();
        slugA.setFightStrategy(normalStrategy);
        assertEquals(normalStrategy, slugA.getFightStrategy());

        // Check its ability of attack.
        // Slug hp = 2, damage = 3. 
        // character hp = 10, damage = 1.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
        character.setDamage(1);

        // At the end of fight, character win with hp = 4.
        currentWorld.runBattles();

        assertEquals(4, character.getHp());
        assertEquals(0, slugA.getHp());
        
    }

    @Test
    public void zombieCreationTest() {

        final double initialHp = 5;
        final double initialDamage = 5;
        final double initialMovingSpeed = 1;
        double initialBattleRadius = 1;
        double initialSupportRadius = 1;
        double chanceOfEffect = 0.3;

        
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
        Zombie myZombie = new Zombie(position00);
        currentWorld.addEnemy(myZombie);
        // Check position.
        assertEquals(position00, myZombie.getPathPosition());
        assertEquals(0, myZombie.getX());
        assertEquals(0, myZombie.getY());

        myZombie.moveDownPath();
        assertEquals(0, myZombie.getX());
        assertEquals(1, myZombie.getY());

        myZombie.moveUpPath();
        assertEquals(0, myZombie.getX());
        assertEquals(0, myZombie.getY());

        /**
         * Need function to check random direction movement in Enemy.java
         */
        // Check move function.
        // myZombie.move();
        
        

        // Check hp.
        assertEquals(initialHp, myZombie.getHp());

        myZombie.setHp(2);
        assertEquals(2, myZombie.getHp());

        // Check damage.
        assertEquals(initialDamage, myZombie.getDamage());

        myZombie.setDamage(3);
        assertEquals(3, myZombie.getDamage());

        // Check moving speed.
        assertEquals(initialMovingSpeed, myZombie.getMovingSpeed());

        myZombie.setMovingSpeed(2);
        assertEquals(2, myZombie.getMovingSpeed());

        // Check support radius and battle radius.
        assertEquals(initialBattleRadius, myZombie.getBattleRadius());

        myZombie.setBattleRadius(2);
        assertEquals(2, myZombie.getBattleRadius());

        assertEquals(initialSupportRadius, myZombie.getSupportRadius());
        myZombie.setSupportRadius(2);
        assertEquals(2, myZombie.getSupportRadius());

        // Check chance of effect.
        assertEquals(chanceOfEffect, myZombie.getChanceOfEffect());

        myZombie.setChanceOfEffect(0.4);
        assertEquals(0.4, myZombie.getChanceOfEffect());

        // Check set is tranced.
        assertEquals(false, myZombie.getTrancedStatus());
        
        myZombie.setIsTranced(true);
        assertEquals(true, myZombie.getTrancedStatus());
        myZombie.setIsTranced(false);

        // Check fight strategy.
        FightStrategy normalStrategy = new BasicFightStrategy();
        myZombie.setFightStrategy(normalStrategy);
        assertEquals(normalStrategy, myZombie.getFightStrategy());

        // Check its ability of attack.
        // Zombie hp = 2, damage = 3. 
        // character hp = 10, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);

        // At the end of fight, character win with hp = 7.
        currentWorld.runBattles();

        assertEquals(7, character.getHp());

        assertEquals(-8, myZombie.getHp());
        
    }

    @Test
    public void vampireCreationTest() {

        final double initialHp = 10;
        final double initialDamage = 10;
        final double initialMovingSpeed = 2;
        double initialBattleRadius = 3;
        double initialSupportRadius = 3;
        double chanceOfEffect = 0.3;

        
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
        Vampire myVampire = new Vampire(position00);
        currentWorld.addEnemy(myVampire);
        // Check position.
        assertEquals(position00, myVampire.getPathPosition());
        assertEquals(0, myVampire.getX());
        assertEquals(0, myVampire.getY());

        myVampire.moveDownPath();
        assertEquals(0, myVampire.getX());
        assertEquals(1, myVampire.getY());

        myVampire.moveUpPath();
        assertEquals(0, myVampire.getX());
        assertEquals(0, myVampire.getY());

        /**
         * Need function to check random direction movement in Enemy.java
         */
        // Check move function.
        // myVampire.move();
        
        

        // Check hp.
        assertEquals(initialHp, myVampire.getHp());

        myVampire.setHp(2);
        assertEquals(2, myVampire.getHp());

        // Check damage.
        assertEquals(initialDamage, myVampire.getDamage());

        myVampire.setDamage(3);
        assertEquals(3, myVampire.getDamage());

        // Check moving speed.
        assertEquals(initialMovingSpeed, myVampire.getMovingSpeed());

        myVampire.setMovingSpeed(2);
        assertEquals(2, myVampire.getMovingSpeed());

        // Check support radius and battle radius.
        assertEquals(initialBattleRadius, myVampire.getBattleRadius());

        myVampire.setBattleRadius(2);
        assertEquals(2, myVampire.getBattleRadius());

        assertEquals(initialSupportRadius, myVampire.getSupportRadius());
        myVampire.setSupportRadius(2);
        assertEquals(2, myVampire.getSupportRadius());

        // Check chance of effect.
        assertEquals(chanceOfEffect, myVampire.getChanceOfEffect());

        myVampire.setChanceOfEffect(0.4);
        assertEquals(0.4, myVampire.getChanceOfEffect());

        // Check set is tranced.
        assertEquals(false, myVampire.getTrancedStatus());
        
        myVampire.setIsTranced(true);
        assertEquals(true, myVampire.getTrancedStatus());
        myVampire.setIsTranced(false);

        // Check campfire in range.
        assertEquals(false, myVampire.getCampfireInRange());

        myVampire.setCampfireInRange(true);
        assertEquals(true, myVampire.getCampfireInRange());
        myVampire.setCampfireInRange(false);

        // Check fight strategy.
        FightStrategy myVampireStrategy = new VampireStrategy();
        myVampire.setFightStrategy(myVampireStrategy);
        assertEquals(myVampireStrategy, myVampire.getFightStrategy());

        // **********NEED TO USE RANDOM TO TEST ATTACK*********************
        
    }

    
}
