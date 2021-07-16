package test;
import java.util.ArrayList;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Buildings.*;
import unsw.loopmania.cards.*;

public class CardTest {
    final Pair<SimpleIntegerProperty, SimpleIntegerProperty> nonPathTile = new Pair<>(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
    final Pair<SimpleIntegerProperty, SimpleIntegerProperty> PathTile = new Pair<>(new SimpleIntegerProperty(32), new SimpleIntegerProperty(0));
    final Pair<SimpleIntegerProperty, SimpleIntegerProperty> nonPathAdjacentTile = new Pair<>(new SimpleIntegerProperty(31), new SimpleIntegerProperty(0));


    public void toBuildingTest(){
    //Create a new vampireCastleCard
    LoopManiaWorld d = new LoopManiaWorld(1, 2, new ArrayList<>());
    SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    //Load a vampireCastleCard
    Card c = d.loadCard(new VampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
    // Test placing on pathTile
    Building newBuilding = d.convertCardToBuildingByCoordinates(c.getX(), c.getY(), 32, 0);
    //Asset newBuilding is null
    assert(newBuilding == null);
    //Should return valid building if on correct tile
    Building newBuilding1 = d.convertCardToBuildingByCoordinates(c.getX(), c.getY(), 31, 0);
    //Check world state to ensure card, building lists are correct
    assert(newBuilding1!=null);
    }
    public void ValidDropTest(){
        //Redo test for all buildings and all cards
    }
}
