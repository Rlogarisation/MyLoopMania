package test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Buildings.*;
import unsw.loopmania.cards.*;

public class CardTest {
    final Pair<Integer, Integer> nonPathTile = new Pair<>(3,0);
    final Pair<Integer, Integer> PathTile = new Pair<>(1,1);
    final Pair<Integer, Integer> nonPathAdjacentTile = new Pair<>(2,0);
    private List <Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>> ();

    


    @Test
    public void isValidDropTest() throws FileNotFoundException{
    //Setup
    LoopManiaWorld d = new LoopManiaWorld(0, 0, orderedPath);
    SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    for(int i=0; i<4; i++){
        orderedPath.add(new Pair<Integer,Integer>(1, i));
    }   
    //Load all test cards
    Card c1 = new VampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    Card c2 = new ZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    Card c3 = new TowerCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    Card c4 = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    Card c5 = new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    Card c6 = new VillageCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    Card c7 = new CampFireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    //VampireCastleCard, ZombiePitCard, TowerCard : Valid drop if non-path adjacent tile only
    assert(c1.validDrop(orderedPath, nonPathAdjacentTile) == true);
    assert(c1.validDrop(orderedPath, PathTile)== false);
    //ZombiePit
    assert(c2.validDrop(orderedPath, nonPathAdjacentTile)== true);
    assert(c2.validDrop(orderedPath, PathTile)== false);
    //Tower
    assert(c3.validDrop(orderedPath, nonPathAdjacentTile)== true);
    assert(c3.validDrop(orderedPath, PathTile)== false);
    
    //Village, Barracks, Trap: Only on pathTiles
    //Village
    assert(c6.validDrop(d.getOrderedPath(), PathTile)== true);
    assert(c6.validDrop(d.getOrderedPath(), nonPathTile)== false);
    //Barracks    
    assert(c4.validDrop(d.getOrderedPath(), PathTile)== true);
    assert(c4.validDrop(d.getOrderedPath(), nonPathTile)== false);
    //Trap
    assert(c5.validDrop(d.getOrderedPath(), PathTile)== true);
    assert(c5.validDrop(d.getOrderedPath(), nonPathTile)== false);

    //Campfire: Only on non-path tiles
    assert(c7.validDrop(d.getOrderedPath(), nonPathTile)== true);
    assert(c7.validDrop(d.getOrderedPath(), PathTile)== false);
    }

    

    public void ValidDropTest(){
        //Redo test for all buildings and all cards
    }
}
