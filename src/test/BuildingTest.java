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
    //Barracks has the same position as the character
    //New ally created with same position as character
    public void BarracksTest_SamePositionAsCharacter(){
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
    //Barracks has a different position than the character
    //No ally should be created
    public void BarracksTest_DifferentPositionAsCharacter(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(0, 1));
        orderedPath.add(new Pair<>(1, 0));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newBarrackA = new Barracks(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newBarrackA);
        Building newBarrackB = new Barracks(new SimpleIntegerProperty(1), new SimpleIntegerProperty(0));
        lmw.addBuildingToBuildingList(newBarrackB);
        List<Ally> allyList = lmw.getAllyList();
        assertEquals(0, allyList.size());
        
        lmw.buildingInteractions();
        assertEquals(0, allyList.size());
        assertEquals(allyList, allyList);
        List<Enemy> blank = new ArrayList<>();
        assertEquals(blank, allyList);
    }

    @Test
    //The character is within the campfire range
    //The character's campfireInRange should be true
    //Once the character moves, campfireInRange should be false
    public void CampfireTest_ChracterInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
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
    //Character is not in range of campfire
    //The character's campfireInRange should be false
    public void CampfireTest_CharacterNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newCampfireA = new Campfire(new SimpleIntegerProperty(2), new SimpleIntegerProperty(2));
        Building newCampfireB = new Campfire(new SimpleIntegerProperty(1), new SimpleIntegerProperty(3));
        lmw.addBuildingToBuildingList(newCampfireA);
        lmw.addBuildingToBuildingList(newCampfireB);
        Character character = lmw.getCharacter();
        assertFalse(character.getCampfireInRange());
        
        lmw.buildingInteractions();
        assertFalse(character.getCampfireInRange());
    }

    @Test
    //There are two vampires that are in range of the campfire
    //Both vampires' campfireInRange should be true
    public void CampfireTest_VampiresInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 0));
        orderedPath.add(new Pair<>(0, 1));
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
    //VampireA is in range and vampireB is not in range of campfire
    //VampireA's campfireInRange should be true and vampireB's is false
    public void CampfireTest_OneVampireInRangeOneNot(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 1));
        orderedPath.add(new Pair<>(1, 2));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition vampPathPosA = new PathPosition(0, orderedPath);
        PathPosition vampPathPosB = new PathPosition(1, orderedPath);
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
        assertTrue(((Vampire)(enemyList.get(0))).getCampfireInRange());
        assertFalse(((Vampire)(enemyList.get(1))).getCampfireInRange());
    }

    @Test
    //Client Requirements wants the hero to be able to access the shop only
    //After 1 full cycle, 2 full cycles, 3 full cycles, 4 full cycles etc.
    //Will only return true for those conditions, false for the rest
    public void HeroCastleTest_CorrectShopCycles(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPos));
        Boolean result;

        //One full cycle
        result = lmw.runHeroCastle();
        assertTrue(result);

        //Two full cycles
        for (int i = 0; i < 1; i++){
            result = lmw.runHeroCastle();
            assertFalse(result);
        }
        result = lmw.runHeroCastle();
        assertTrue(result);

        //Three full cycles
        for (int i = 0; i < 2; i++){
            result = lmw.runHeroCastle();
            assertFalse(result);
        }
        result = lmw.runHeroCastle();
        assertTrue(result);

        //Four full cycles
        for (int i = 0; i < 3; i++){
            result = lmw.runHeroCastle();
            assertFalse(result);
        }
        result = lmw.runHeroCastle();
        assertTrue(result);
    }

    @Test
    //Update zombiePit to set spawnZombie to true when a cycle is complete
    public void HeroCastleTest_ZombieSpawn(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPos));
        Building newZombiePit = new ZombiePit(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        lmw.addBuildingToBuildingList(newZombiePit);
        HeroCastle hc = lmw.getHeroCastle();
        hc.attach(((ZombiePit)newZombiePit));
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        
        lmw.runHeroCastle();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
    }

    @Test
    //Update numCycles in vampire when a cycle is complete
    //After doing this 5 times, the spawnVampire should be true
    public void HeroCastleTest_VampireSpawn(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPos));
        Building newVampireCastle = new VampireCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        lmw.addBuildingToBuildingList(newVampireCastle);
        HeroCastle hc = lmw.getHeroCastle();
        hc.attach(((VampireCastle)newVampireCastle));
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        
        for (int i = 0; i < 4; i++){
            lmw.runHeroCastle();
            assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        }

        lmw.runHeroCastle();
        assertTrue(((VampireCastle)newVampireCastle).getSpawnVampire());
    }

    @Test
    //Hero's Castle will have a different position than the character
    //Opening the shop for the first full cycle should be false
    public void HeroCastleTest_DifferentPositionAsCharacter(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        orderedPath.add(new Pair<>(1, 0));
        orderedPath.add(new Pair<>(0, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(1, orderedPath);
        lmw.setCharacter(new Character(charPos));

        Boolean result = lmw.runHeroCastle();
        assertFalse(result);

        charPos = new PathPosition(2, orderedPath);
        lmw.setCharacter(new Character(charPos));

        result = lmw.runHeroCastle();
        assertFalse(result);
    }

    @Test
    public void HeroCastle_SpawnDoggie(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        orderedPath.add(new Pair<>(0, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPos));

        //not enough cycles
        lmw.getCharacter().setCycleCount(18);
        lmw.runHeroCastle();
        assertEquals(0, lmw.getEnemyList().size());

        //should spawn -> enough cycles
        lmw.runHeroCastle();
        assertEquals(1, lmw.getEnemyList().size());
        assert(lmw.getEnemyList().get(0) instanceof Doggie);
        assert(lmw.getHeroCastle().getSpawnDoggie());
    }

    @Test
    public void HeroCastle_InvalidSpawnDoggie(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPos));
        //not enough cycles
        lmw.getCharacter().setCycleCount(18);
        lmw.runHeroCastle();
        assertEquals(0, lmw.getEnemyList().size());

        //should not spawn -> can't spawn enemy on Hero's Castle
        lmw.runHeroCastle();
        assertEquals(0, lmw.getEnemyList().size());
        assertFalse(lmw.getHeroCastle().getSpawnDoggie());
    }

    @Test
    public void HeroCastle_SpawnElanMuske(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(0, 0));
        orderedPath.add(new Pair<>(0, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPos));
        //not enough xp
        lmw.runHeroCastle();
        assertEquals(0, lmw.getEnemyList().size());

        //not enough cycles
        lmw.getCharacter().addXp(10000);
        lmw.getCharacter().setCycleCount(38);
        lmw.runHeroCastle();
        assertEquals(0, lmw.getEnemyList().size());

        //successfull
        lmw.runHeroCastle();
        assertEquals(1, lmw.getEnemyList().size());
        assert(lmw.getEnemyList().get(0) instanceof ElanMuske);
        assert(lmw.getHeroCastle().getSpawnElanMuske());
    }

    @Test
    //Character is in range of the tower
    //The character's towerDamge should increase from 0 to 5
    public void TowerTest_CharacterInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newTower = new Tower(new SimpleIntegerProperty(4), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTower);
        Character character = lmw.getCharacter();
        assertEquals(0, character.getTowerDamage());
        
        lmw.buildingInteractions();
        assertEquals(5, character.getTowerDamage());
    }

    @Test
    //Character is not in range of the tower
    //The character's towerDamage should remain at 0
    public void TowerTest_CharacterNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newTower = new Tower(new SimpleIntegerProperty(5), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTower);
        Character character = lmw.getCharacter();
        assertEquals(0, character.getTowerDamage());
        
        lmw.buildingInteractions();
        assertEquals(0, character.getTowerDamage());
    }

    @Test
    //There are two towers that have the character in range and one doesn't
    //The character's towerDamage should increase from 0 to 10
    public void TowerTest_TwoTowersCharacterInRangeOneNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newTowerA = new Tower(new SimpleIntegerProperty(0), new SimpleIntegerProperty(1));
        Building newTowerB = new Tower(new SimpleIntegerProperty(3), new SimpleIntegerProperty(1));
        Building newTowerC = new Tower(new SimpleIntegerProperty(5), new SimpleIntegerProperty(1));
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
    //Testing when a trap has the same position as an enemy, but doesn't kill them
    //For a vampire, the trap doesn't deal enough damage to kill it
    //Checking that the size of the enemyList remains the same
    //The size of the buildingList should decrease from 1 to 0 - trap gets destroyed after use
    //Check that the enemiesKilled list in the newChanges is 0
    public void TrapTest_AttackEnemyNoKill(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition vampPathPos = new PathPosition(0, orderedPath);
        Enemy vamp = new Vampire(vampPathPos);
        lmw.addEnemy(vamp);
        lmw.setCharacter(new Character(vampPathPos));
        Building newTrap = new Trap(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTrap);
        assertEquals(1, lmw.getBuildingList().size());
        
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertEquals(0, lmw.getBuildingList().size());
        assertEquals(0, newChanges.getEnemiesKilledByTrap().size());
    }

    @Test
    //The trap has the same position as a slug
    //The trap should kill and slug and get destroyed
    //Check that the buildingList and enemyList size decreases from 1 to 0
    //Check that the enemiesKilled list in newChange is 1
    public void TrapTest_AttackEnemyAndKill(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition slugPathPos = new PathPosition(0, orderedPath);
        Enemy slug = new Slug(slugPathPos);
        lmw.addEnemy(slug);
        assertEquals(1, lmw.getEnemyList().size());
        lmw.setCharacter(new Character(slugPathPos));
        Building newTrap = new Trap(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTrap);
        assertEquals(1, lmw.getBuildingList().size());
        
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertEquals(0, lmw.getBuildingList().size());
        assertEquals(1, newChanges.getEnemiesKilledByTrap().size());
        assertEquals(0, lmw.getEnemyList().size());
    }

    @Test
    //Two slugs that are not in range of the trap
    //The enemyList remains at 2 and the buildingList remains at 1
    //The enemiesKilled list in newChanges should be 0
    public void TrapTest_EnemyNotInRange(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(2, 1));
        orderedPath.add(new Pair<>(2, 2));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition slugPathPosA = new PathPosition(0, orderedPath);
        PathPosition slugPathPosB = new PathPosition(2, orderedPath);
        Enemy slugA = new Slug(slugPathPosA);
        Enemy slugB = new Slug(slugPathPosB);
        lmw.addEnemy(slugA);
        lmw.addEnemy(slugB);
        assertEquals(2, lmw.getEnemyList().size());
        lmw.setCharacter(new Character(slugPathPosA));
        Building newTrap = new Trap(new SimpleIntegerProperty(2), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newTrap);
        assertEquals(1, lmw.getBuildingList().size());
        
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertEquals(1, lmw.getBuildingList().size());
        assertEquals(0, newChanges.getEnemiesKilledByTrap().size());
        assertEquals(2, lmw.getEnemyList().size());
    }

    @Test
    //For the first 4 updates and buildingInteractions, no vampires are spawned
    //On the fifth one, vampire will be spawned
    public void VampireCastleTest_ValidSpawnVampire(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(4, 3));
        orderedPath.add(new Pair<>(4, 5));
        orderedPath.add(new Pair<>(5, 4));
        orderedPath.add(new Pair<>(3, 4));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newVampireCastle = new VampireCastle(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        lmw.addBuildingToBuildingList(newVampireCastle);
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        assertEquals(0, lmw.getEnemyList().size());

        BuildingInfo newChanges = lmw.buildingInteractions();
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());

        ((VampireCastle)newVampireCastle).update();
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        newChanges = lmw.buildingInteractions();
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());

        ((VampireCastle)newVampireCastle).update();
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        newChanges = lmw.buildingInteractions();
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());

        ((VampireCastle)newVampireCastle).update();
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        newChanges = lmw.buildingInteractions();
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());

        ((VampireCastle)newVampireCastle).update();
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        newChanges = lmw.buildingInteractions();
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());

        //This will be the 5th time update is called -> spawnVampire = true
        ((VampireCastle)newVampireCastle).update();
        assertTrue(((VampireCastle)newVampireCastle).getSpawnVampire());
        newChanges = lmw.buildingInteractions();
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        assertEquals(1, lmw.getEnemyList().size());
        assertEquals(1, newChanges.getNewEmeies().size());
        Enemy newVampire = lmw.getEnemyList().get(0);
        assertEquals(4, newVampire.getX());
        assertEquals(3, newVampire.getY());
        assertEquals(newVampire, newChanges.getNewEmeies().get(0));
    }

    @Test
    //Testing if Pos is null in VampireCastle
    //spawnVampire field will set true and no vampire has been spawned
    //There is a seperate test for getSpecificSpawnPosition + isEnemyOnPath
    public void VampireCastleTest_NullPosition(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(4, 5));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newVampireCastle = new VampireCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        lmw.addBuildingToBuildingList(newVampireCastle);
        assertFalse(((VampireCastle)newVampireCastle).getSpawnVampire());
        assertEquals(0, lmw.getEnemyList().size());
        ((VampireCastle)newVampireCastle).setSpawnVampire(true);
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertTrue(((VampireCastle)newVampireCastle).getSpawnVampire());
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());
    }

    @Test
    //Character has the same position as the village
    //The village will increase the character's health by 10
    public void VillageTest_SamePositionAsCharacter(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newVillage = new Village(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newVillage);
        lmw.getCharacter().setHp(50);
        assertEquals(50, lmw.getCharacter().getHp());

        lmw.buildingInteractions();
        assertEquals(60, lmw.getCharacter().getHp());
    }

    @Test
    //Character has the same postion as the village
    //The village will increase the character's health
    //The character's health caps at 100
    //Therefore, the character's health should increase from 95 to 100, not 105
    public void VillageTest_HealCharacterPast100(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newVillage = new Village(new SimpleIntegerProperty(1), new SimpleIntegerProperty(1));
        lmw.addBuildingToBuildingList(newVillage);
        lmw.getCharacter().setHp(95);
        assertEquals(95, lmw.getCharacter().getHp());

        lmw.buildingInteractions();
        assertEquals(100, lmw.getCharacter().getHp());
    }

    @Test
    //Character has a different position than the village
    //The character's health should remain the same
    public void VillageTest_DifferentPositionAsCharacter(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(1, 1));
        orderedPath.add(new Pair<>(2, 1));
        orderedPath.add(new Pair<>(1, 2));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newVillageA = new Village(new SimpleIntegerProperty(2), new SimpleIntegerProperty(1));
        Building newVillageB = new Village(new SimpleIntegerProperty(1), new SimpleIntegerProperty(2));
        lmw.addBuildingToBuildingList(newVillageA);
        lmw.addBuildingToBuildingList(newVillageB);
        lmw.getCharacter().setHp(50);
        assertEquals(50, lmw.getCharacter().getHp());

        lmw.buildingInteractions();
        assertEquals(50, lmw.getCharacter().getHp());
    }

    @Test
    //First: spawnZombie is false -> no zombie is spawned
    //Second: called update to make spawnZombie true -> zombie is spawned
    public void ZombiePitTest_ValidSpawnZombie(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(4, 5));
        orderedPath.add(new Pair<>(4, 3));
        orderedPath.add(new Pair<>(5, 4));
        orderedPath.add(new Pair<>(3, 4));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newZombiePit = new ZombiePit(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        lmw.addBuildingToBuildingList(newZombiePit);
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(0, lmw.getEnemyList().size());

        BuildingInfo newChanges = lmw.buildingInteractions();
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());

        ((ZombiePit)newZombiePit).update();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        newChanges = lmw.buildingInteractions();
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(1, lmw.getEnemyList().size());
        assertEquals(1, newChanges.getNewEmeies().size());
        Enemy newZombie = lmw.getEnemyList().get(0);
        assertEquals(4, newZombie.getX());
        assertEquals(5, newZombie.getY());
        assertEquals(newZombie, newChanges.getNewEmeies().get(0));
    }

    @Test
    //Testing if Pos is null in ZombiePit
    //spawnZombie field will be true and no zombie has been spawned
    //There is a seperate test for getSpecificSpawnPosition + isEnemyOnPath
    public void ZombiePitTest_NullPosition(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(4, 5));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newZombiePit = new ZombiePit(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        lmw.addBuildingToBuildingList(newZombiePit);
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(0, lmw.getEnemyList().size());
        ((ZombiePit)newZombiePit).update();
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(0, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());
    }

    @Test
    //Test this by trying to spawn 5 zombies
    //After 4 sucessful spawns on the top, bottom, left and right positions
    //On the 5th attempt, isEnemyOnPath will return false - invalid position
    //After 5 attempts to spawn a zombie, there should only be 4
    public void getSpecificSpawnPositionAndIsEnemyOnPathTest(){
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<>();
        orderedPath.add(new Pair<>(4, 3));
        orderedPath.add(new Pair<>(4, 5));
        orderedPath.add(new Pair<>(3, 4));
        orderedPath.add(new Pair<>(5, 4));
        LoopManiaWorld lmw = newLmw(orderedPath);
        PathPosition charPathPos = new PathPosition(0, orderedPath);
        lmw.setCharacter(new Character(charPathPos));
        Building newZombiePit = new ZombiePit(new SimpleIntegerProperty(4), new SimpleIntegerProperty(4));
        lmw.addBuildingToBuildingList(newZombiePit);
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(0, lmw.getEnemyList().size());
        
        //Spawning First Zombie
        ((ZombiePit)newZombiePit).update();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        BuildingInfo newChanges = lmw.buildingInteractions();
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(1, lmw.getEnemyList().size());
        assertEquals(1, newChanges.getNewEmeies().size());
        Enemy newZombie = lmw.getEnemyList().get(0);
        assertEquals(4, newZombie.getX());
        assertEquals(3, newZombie.getY());
        assertEquals(newZombie, newChanges.getNewEmeies().get(0));

        //Spawning Second Zombie
        ((ZombiePit)newZombiePit).update();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        newChanges = lmw.buildingInteractions();
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(2, lmw.getEnemyList().size());
        assertEquals(1, newChanges.getNewEmeies().size());
        newZombie = lmw.getEnemyList().get(1);
        assertEquals(4, newZombie.getX());
        assertEquals(5, newZombie.getY());
        assertEquals(newZombie, newChanges.getNewEmeies().get(0));

        //Spawning Third Zombie
        ((ZombiePit)newZombiePit).update();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        newChanges = lmw.buildingInteractions();
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(3, lmw.getEnemyList().size());
        assertEquals(1, newChanges.getNewEmeies().size());
        newZombie = lmw.getEnemyList().get(2);
        assertEquals(3, newZombie.getX());
        assertEquals(4, newZombie.getY());
        assertEquals(newZombie, newChanges.getNewEmeies().get(0));

        //Spawning Fourth Zombie
        ((ZombiePit)newZombiePit).update();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        newChanges = lmw.buildingInteractions();
        assertFalse(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(4, lmw.getEnemyList().size());
        assertEquals(1, newChanges.getNewEmeies().size());
        newZombie = lmw.getEnemyList().get(3);
        assertEquals(5, newZombie.getX());
        assertEquals(4, newZombie.getY());
        assertEquals(newZombie, newChanges.getNewEmeies().get(0));

        ///Try to spawn Fifth Zombie - will not work due to no valid path
        ((ZombiePit)newZombiePit).update();
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        newChanges = lmw.buildingInteractions();
        //spawnZombie should remain true because zombie has not been spawned
        assertTrue(((ZombiePit)newZombiePit).getSpawnZombie());
        assertEquals(4, lmw.getEnemyList().size());
        assertEquals(0, newChanges.getNewEmeies().size());
    }

    @Test
    //Testing the BuildingInteractions method in LoopManiaWorld
    //This method needs to give the buildingEffect methods pathPosition, enemyList and character
    //If there is no character, nothing should happen - newChanges should have empty lists
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
