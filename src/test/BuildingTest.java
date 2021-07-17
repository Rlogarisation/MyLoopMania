package test;

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
        System.out.println(allyList);
        assert(allyList.size() == 0);
        BuildingInfo newChanges = lmw.buildingInteractions();
        System.out.println(allyList);
        assert(allyList.size() == 1);
        
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
        System.out.println(allyList);
        assert(allyList.size() == 0);
        BuildingInfo newChanges = lmw.buildingInteractions();
        System.out.println(allyList);
        assert(allyList.size() == 0);
        assertEquals(allyList, allyList);
    }

    @Test
    public void CampfireTest(){

    }

    @Test
    public void HeroCastleTest(){

    }

    @Test
    public void TowerTest(){

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
}
