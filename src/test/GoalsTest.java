package test;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.PathPosition;
import unsw.loopmania.Character;
public class GoalsTest {
    @Test
    public void testBasicGoals(){
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

        //Load basic goals JSONObject into world
        JSONObject goalxp = new JSONObject();
        goalxp.put("goal", "experience");
        goalxp.put("quantity", 50000);
        currentWorld.setGoals(goalxp);

        //Assert goals have been loaded in
        assert(currentWorld.hasAchievedGoal() == false);

        //set xp to goal amount
        myHero.addXp(50001);

        //assert goal has been achieved
        assert(currentWorld.hasAchievedGoal() == true);

        //Repeat tests for gold, boss and cycle
        //Gold
        JSONObject goalGold = new JSONObject();
        goalGold.put("goal", "gold");
        goalGold.put("quantity", 50000);
        currentWorld.setGoals(goalGold);
        assert(currentWorld.hasAchievedGoal() == false);
        myHero.addGold(50001);
        assert(currentWorld.hasAchievedGoal() == true);

        //Boss
        JSONObject goalBoss = new JSONObject();
        goalBoss.put("goal", "bosses");
        currentWorld.setGoals(goalBoss);
        assert(currentWorld.hasAchievedGoal() == false);
        myHero.setBossesKilled(2);
        assert(currentWorld.hasAchievedGoal() == true);

        //cycleCount
        JSONObject goalCycle = new JSONObject();
        goalCycle.put("goal", "cycles");
        goalCycle.put("quantity", "20");
        currentWorld.setGoals(goalCycle);
        assert(currentWorld.hasAchievedGoal() == false);
        myHero.setCycleCount(21);
        assert(currentWorld.hasAchievedGoal() == true);

    }
    @Test
    public void testComplexGoals(){
        JSONObject goalGold = new JSONObject();
        JSONObject goalBoss = new JSONObject();
        JSONObject goalCycle = new JSONObject();
        JSONObject goalxp = new JSONObject();
        JSONObject goalAnd = new JSONObject();
        JSONObject goalOr = new JSONObject(); 
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

        //Load basic goals JSONObject into world
        goalGold.put("goal", "gold");
        goalGold.put("quantity", 50000);
        
        goalBoss.put("goal", "bosses");
        goalCycle.put("goal", "cycles");
        goalCycle.put("quantity", "20");
        goalxp.put("goal", "experience");
        goalxp.put("quantity", 50000);

        //Set up complex goals
        goalAnd.put("goal", "AND");
        goalOr.put("goal", "OR");
        
        //create subgoals arrays
        JSONArray subGoals1 = new JSONArray();
        subGoals1.put(goalCycle);
        subGoals1.put(goalOr);

        JSONArray subGoals2 = new JSONArray();
        subGoals2.put(goalxp);
        subGoals2.put(goalBoss);

        //compose complex goal
        goalAnd.put("subgoals", subGoals1);
        goalOr.put("subgoals", subGoals2);

        //set goal into world
        currentWorld.setGoals(goalAnd);

        //assert goals are false
        assert(currentWorld.hasAchievedGoal() == false);


        //set xp and boss goal and assert still false
        myHero.addXp(50001);
        myHero.setBossesKilled(2);
        assert(currentWorld.hasAchievedGoal() == false);
        //set cycle count to goal and assert true
        myHero.setCycleCount(21);
        assert(currentWorld.hasAchievedGoal() == true);
    }


    @Test
    public void testMoreComplexGoals(){
        JSONObject goalGold = new JSONObject();
        JSONObject goalBoss = new JSONObject();
        JSONObject goalCycle = new JSONObject();
        JSONObject goalxp = new JSONObject();
        JSONObject goalAnd = new JSONObject();
        JSONObject goalOr = new JSONObject(); 
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

        //Load basic goals JSONObject into world
        goalGold.put("goal", "gold");
        goalGold.put("quantity", 50000);
        
        goalBoss.put("goal", "bosses");
        goalCycle.put("goal", "cycles");
        goalCycle.put("quantity", "20");
        goalxp.put("goal", "experience");
        goalxp.put("quantity", 50000);

        //Set up complex goals
        goalAnd.put("goal", "AND");
        goalOr.put("goal", "OR");
        
        //create subgoals arrays
        JSONArray subGoals1 = new JSONArray();
        subGoals1.put(goalCycle);
        subGoals1.put(goalOr);

        JSONArray subGoals2 = new JSONArray();
        subGoals2.put(goalGold);
        subGoals2.put(goalBoss);

        //compose complex goal
        goalAnd.put("subgoals", subGoals1);
        goalOr.put("subgoals", subGoals2);

        //set goal into world
        currentWorld.setGoals(goalAnd);

        //assert goals are false
        assert(currentWorld.hasAchievedGoal() == false);


        //set xp and boss goal and assert still false
        myHero.addXp(50001);
        myHero.addGold(50001);
        assert(currentWorld.hasAchievedGoal() == false);
        //set cycle count to goal and assert true
        myHero.setCycleCount(21);
        assert(currentWorld.hasAchievedGoal() == true);
    }

}
