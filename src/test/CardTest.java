package test;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Buildings.*;
import unsw.loopmania.cards.*;

public class CardTest {
    final Pair<Integer, Integer> nonPathTile = new Pair<>(3,0);
    final Pair<Integer, Integer> PathTile = new Pair<>(1,1);
    final Pair<Integer, Integer> nonPathAdjacentTile = new Pair<>(2,0);
    private List <Pair<Integer,Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>> ();
    SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    


    @Test
    public void isValidDropTest(){
    //Setup
    LoopManiaWorld d = new LoopManiaWorld(0, 0, orderedPath);
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

    
    @Test
    public void toBuildingTest(){
        Card c1 = new VampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Card c2 = new ZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Card c3 = new TowerCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Card c4 = new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Card c5 = new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Card c6 = new VillageCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        Card c7 = new CampFireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        assert(c1.toBuilding(x, y) instanceof VampireCastle);
        assert(c2.toBuilding(x, y) instanceof ZombiePit);
        assert(c3.toBuilding(x, y) instanceof Tower);
        assert(c4.toBuilding(x, y) instanceof Barracks);
        assert(c5.toBuilding(x, y) instanceof Trap);
        assert(c6.toBuilding(x, y) instanceof Village);
        assert(c7.toBuilding(x, y) instanceof Campfire);
    }



@Test
    public void toBuildingFromCoordinatesTest(){
        //Test if card doesn't exist
        for(int i=0; i<4; i++){
            orderedPath.add(new Pair<Integer,Integer>(1, i));
        }  
        LoopManiaWorld d = new LoopManiaWorld(8, 0, orderedPath);   
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(1, 1));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        Character myHero = new Character(position00);
        d.setCharacter(myHero);
        Building b1 = d.convertCardToBuildingByCoordinates(0, 0, nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b1==null);
        //Test buildings placed on same location
        Card c1 = d.loadCard(new VampireCastleCard(x, y));
        Card c2 = d.loadCard(new ZombiePitCard(x,y));
        Card c3 = d.loadCard(new TowerCard(x,y));
        Card c4 = d.loadCard(new CampFireCard(x,y));
        Card c5 = d.loadCard(new TrapCard(x,y));
        Card c6 = d.loadCard(new VillageCard(x,y));
        Card c7 = d.loadCard(new BarracksCard(x,y));
        //Should successfully be added to world
        Building b2 = d.convertCardToBuildingByCoordinates(c1.getX(), c1.getY(), 2, 0);
        assert(b2!=null);
        // Place c2 on same location as c1, should return null
        Building b3 = d.convertCardToBuildingByCoordinates(c2.getX(), c2.getY(), nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b3==null);
        //Repeat the same for other card types
        Building b4 = d.convertCardToBuildingByCoordinates(c3.getX(), c3.getY(), nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b4==null);
        Building b5 = d.convertCardToBuildingByCoordinates(c4.getX(), c4.getY(), nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b5==null);
        Building b6 = d.convertCardToBuildingByCoordinates(c5.getX(), c5.getY(), nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b6==null);
        Building b7 = d.convertCardToBuildingByCoordinates(c6.getX(), c6.getY(), nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b7==null);
        Building b8 = d.convertCardToBuildingByCoordinates(c7.getX(), c7.getY(), nonPathAdjacentTile.getValue0(), nonPathAdjacentTile.getValue1());
        assert(b8==null);

        // ZombiePit spawns
        Building zombiePit = d.convertCardToBuildingByCoordinates(c2.getX(), c2.getY(), 2, 1);
        assert(zombiePit instanceof ZombiePit);

    }


    @Test
    public void maxCardsTest(){
        for(int i=0; i<4; i++){
            orderedPath.add(new Pair<Integer,Integer>(1, i));
        }  
        //Limit is 5 cards currently
        LoopManiaWorld d = new LoopManiaWorld(5, 0, orderedPath);  
        int index00InPath = orderedPath.indexOf(new Pair<Integer, Integer>(1, 1));
        PathPosition position00 = new PathPosition(index00InPath, orderedPath);
        Character myHero = new Character(position00);
        d.setCharacter(myHero);
        Card c1 = d.loadCard(new VampireCastleCard(x, y));
        Card c2 = d.loadCard(new ZombiePitCard(x,y));
        Card c3 = d.loadCard(new TowerCard(x,y));
        Card c4 = d.loadCard(new CampFireCard(x,y));
        Card c5 = d.loadCard(new TrapCard(x,y));
        assert(d.getCardList().size() == 5);
        //Card loaded over limit
        Card c6 = d.loadCard(new VillageCard(x,y));
        //Assert c1 has been removed and size is still 5
        assert(!d.getCardList().contains(c1) && d.getCardList().size() == 5);
        //Check character recieved rewards
        assert(myHero.getGold()==5 && myHero.getXp()== 10);
        
    }
}
