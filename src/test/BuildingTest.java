package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Buildings.*;

public class BuildingTest {

    public LoopManiaWorld newLmw(List<Pair<Integer, Integer>> orderedPath){
        return (new LoopManiaWorld(10, 10, orderedPath));
    }

    @Test
    public void BarracksTest_SamePositionAsChar(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newBarrack = new Barracks(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newBarrack);
        List<Ally> allyList = lmw.getAllyList();
        assertEquals(0, allyList.size());
        lmw.buildingInteractions();
        assertEquals(1, allyList.size());
        Ally a = allyList.get(0);
        assertEquals(1, a.getX());
        assertEquals(1, a.getY());
        
    }

    @Test
    public void BarracksTest_DifferentPositionAsChar(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(0, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newBarrack = new Barracks(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newBarrack);
        List<Ally> allyList = lmw.getAllyList();
        assertEquals(0, allyList.size());
        lmw.buildingInteractions();
        assertEquals(0, allyList.size());
        assertEquals(allyList, allyList);
        List<Enemy> blank = new ArrayList<>();
        assertEquals(blank, allyList);
    }

    @Test
    public void CampfireTest_ChracterInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(0, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newCampfire = new Campfire(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newCampfire);
        Character character = lmw.getCharacter();
        assertFalse(character.getCampfireInRange());
        lmw.buildingInteractions();
        assertTrue(character.getCampfireInRange());
        //check that when the player moves, the campfireInRange is reset to false
        lmw.runTickMoves();
        assertFalse(character.getCampfireInRange());
    }

    @Test
    public void CampfireTest_CharacterNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(2, 2));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newCampfire = new Campfire(new SimpleIntegerProperty(2), new SimpleIntegerProperty(2));
        lmw.addBuildingToBuildingList(newCampfire);
        Character character = lmw.getCharacter();
        assertFalse(character.getCampfireInRange());
        lmw.buildingInteractions();
        assertFalse(character.getCampfireInRange());
    }

    @Test
    public void CampfireTest_VampiresInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(1, 2));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition vampPathPosA = new PathPosition(0, orderedPath);
        PathPosition vampPathPosB = new PathPosition(1, orderedPath);
        Enemy vampA = new Vampire(vampPathPosA);
        Enemy vampB = new Vampire(vampPathPosB);
        lmw.addEnemy(vampA);
        lmw.addEnemy(vampB);
        lmw.setCharacter(new Character(vampPathPosA));
        Building newCampfire = new Campfire(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newCampfire);
        List<Enemy> enemyList = lmw.getEnemyList();
        assertEquals(2, lmw.getEnemyList().size());
        assertFalse(((Vampire)(enemyList.get(0))).getCampfireInRange());
        assertFalse(((Vampire)(enemyList.get(1))).getCampfireInRange());
        lmw.buildingInteractions();
        assertTrue(((Vampire)(enemyList.get(0))).getCampfireInRange());
        assertTrue(((Vampire)(enemyList.get(1))).getCampfireInRange());

    }

    @Test
    public void CampfireTest_OneVampireInRangeOneNot(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        orderedPath.add(new Pair<>(0, 1));
        orderedPath.add(new Pair<>(1, 2));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition vampPathPosA = new PathPosition(1, orderedPath);
        PathPosition vampPathPosB = new PathPosition(2, orderedPath);
        Enemy vampA = new Vampire(vampPathPosA);
        Enemy vampB = new Vampire(vampPathPosB);
        lmw.addEnemy(vampA);
        lmw.addEnemy(vampB);
        lmw.setCharacter(new Character(vampPathPosA));
        Building newCampfire = new Campfire(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        lmw.addBuildingToBuildingList(newCampfire);
        List<Enemy> enemyList = lmw.getEnemyList();
        assertEquals(2, lmw.getEnemyList().size());
        assertFalse(((Vampire)(enemyList.get(0))).getCampfireInRange());
        assertFalse(((Vampire)(enemyList.get(1))).getCampfireInRange());
        lmw.buildingInteractions();
        //VampireA should be in range of campfire, vampireB should not
        assertTrue(((Vampire)(enemyList.get(0))).getCampfireInRange());
        assertFalse(((Vampire)(enemyList.get(1))).getCampfireInRange());
    }

    @Test
    public void HeroCastleTest(){
        
    }

    @Test
    public void TowerTest_CharacterInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(3, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newTower = new Tower(new SimpleIntegerProperty(3), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTower);
        Character character = lmw.getCharacter();
        assertEquals(0, character.getTowerDamage());
        lmw.buildingInteractions();
        assertEquals(5, character.getTowerDamage());
    }

    @Test
    public void TowerTest_CharacterNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(4, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newTower = new Tower(new SimpleIntegerProperty(4), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTower);
        Character character = lmw.getCharacter();
        assertEquals(0, character.getTowerDamage());
        lmw.buildingInteractions();
        assertEquals(0, character.getTowerDamage());
    }

    @Test
    public void TowerTest_TwoTowersCharacterInRangeOneNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(0, 1));
        orderedPath.add(new Pair<>(3, 1));
        orderedPath.add(new Pair<>(4, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newTowerA = new Tower(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        Building newTowerB = new Tower(new SimpleIntegerProperty(3), new SimpleIntegerProperty(1));
        Building newTowerC = new Tower(new SimpleIntegerProperty(4), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTowerA);
        lmw.addBuildingToBuildingList(newTowerB);
        lmw.addBuildingToBuildingList(newTowerC);
        Character character = lmw.getCharacter();
        assertEquals(0, character.getTowerDamage());
        lmw.buildingInteractions();
        assertEquals(10, character.getTowerDamage());
        //check that when the player moves, the towerDamage is reset
        lmw.runTickMoves();
        assertEquals(0, character.getTowerDamage());
    }

    @Test
    public void TrapTest(){

    }

    @Test
    public void VampireCastleTest(){

    }

    @Test
    public void VillageTest(){

    }

    @Test
    public void ZombiePitTest(){

    }

    @Test
    public void BuildingInteractions_InvalidCharacter(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1)); 
        LoopManiaWorld lmw = newLmw(orderedPath);
        Building newTower = new Tower(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTower);
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertEquals(0, newChanges.getEnemiesKilledByTrap().size());
        assertEquals(0, newChanges.getNewEmeies().size());
    }
}
