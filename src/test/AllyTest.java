package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;

public class AllyTest {

    final double initialHp = 10;
    final double initialDamage = 10;
    final double initialMovingSpeed = 2;
    final double initialXp = 0; 
    final double initialGold = 0; 
    final double initialArmour = 0;

    @Test
    public void testAllyCreation(){
        //Setup for ally to exist
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
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 0));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        Character myHero = new Character(position00);
        currentWorld.setCharacter(myHero);
        //add 3 allies to world
        Ally a1 = currentWorld.addAlly(myHero.getPathPosition());
        Ally a2 = currentWorld.addAlly(myHero.getPathPosition());
        Ally a3 = currentWorld.addAlly(myHero.getPathPosition());
        //Assert 3 allies added to current world
        assertEquals(currentWorld.getAllyList().size(), 3);
        //Remove all 3 allies
        currentWorld.removeAlly(a1);
        currentWorld.removeAlly(a2);
        currentWorld.removeAlly(a3);
        //Assert allyList is empty
        assertEquals(currentWorld.getAllyList().size(), 0);

        //TrancedAlly repeat above
        Slug slugA = new Slug(position00);
        currentWorld.addEnemy(slugA);
        //add a tranced ally to world
        Ally ta1 = currentWorld.addTrancedAlly(myHero.getPathPosition(), slugA);
        //Assert tranced ally added to current world
        assertEquals(currentWorld.getTrancedAllyList().size(), 1);
        //Remove tranced ally
        currentWorld.removeAlly(ta1);
        //Assert TrancedAllyList is empty
        assertEquals(currentWorld.getTrancedAllyList().size(), 0);


    }
    @Test
    public void testTrancedAlly(){
        //Setup for ally to exist
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
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(0, 0));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        Character myHero = new Character(position00);
        currentWorld.setCharacter(myHero);


        Slug slugA = new Slug(position00);
        Slug slugB = new Slug(position00);
        Slug SlugC = new Slug(position00);
        Slug SlugD = new Slug(position00);
        currentWorld.addEnemy(slugA);
        currentWorld.addEnemy(slugB);
        currentWorld.addEnemy(SlugC);
        currentWorld.addEnemy(SlugD);
        //Turn slugA into tranced Ally
        slugA.setIsTranced(true);
        //Run battles
        List<Enemy> dead = currentWorld.runBattles();
        //Tranced List should be empty
        assert(currentWorld.getTrancedAllyList().isEmpty());
        //Enemy List should be empty
        assert(currentWorld.getEnemyList().isEmpty());
        //Defeated should have slug B,C,D. Slug A was tranced and died as ally
        assert(dead.contains(slugB) && dead.contains(SlugC) && dead.contains(SlugD));
        System.out.println(myHero.getHp());
        //If not tranced, Character would be hit by 2 extra enemies and health would be 2
        assert(myHero.getHp() == 6);

        //Add zombie and vampires

    }

}
