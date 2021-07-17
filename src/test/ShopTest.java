package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.LoopManiaWorld.GAME_MODE;

public class ShopTest {
    
    /**
     * This test checks if the items are included in the shop.
     */
    @Test
    public void testShopItemList() {
        HeroCastle heroCastle = new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0));
        HashMap<String,StaticEntity> shopItemList = heroCastle.getShopItems();
        assertTrue(shopItemList.containsKey("Sword"));
        assertTrue(shopItemList.containsKey("Staff"));
        assertTrue(shopItemList.containsKey("Stake"));
        assertTrue(shopItemList.containsKey("Armour"));
        assertTrue(shopItemList.containsKey("Shield"));
        assertTrue(shopItemList.containsKey("Helmet"));
        assertTrue(shopItemList.containsKey("Health Potion"));

        assertTrue(shopItemList.get("Sword") instanceof Sword);
        assertTrue(shopItemList.get("Staff") instanceof Staff);
        assertTrue(shopItemList.get("Stake") instanceof Stake);
        assertTrue(shopItemList.get("Armour") instanceof Armour);
        assertTrue(shopItemList.get("Shield") instanceof Shield);
        assertTrue(shopItemList.get("Helmet") instanceof Helmet);
        assertTrue(shopItemList.get("Health Potion") instanceof HealthPotion);
    }

    /**
     * This test checks if the character can buy items from the shop
     * and balance remaining after purchasing items is correct.
     */
    @Test
    public void testBuyItems() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(10000);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);

        d.buyOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),9600);

        d.buyOneItemBycoordinates(0,1);
        assertEquals(c.getGold(),9100);

        d.buyOneItemBycoordinates(0,2);
        assertEquals(c.getGold(),8600);
        
        d.buyOneItemBycoordinates(0,3);
        assertEquals(c.getGold(),8300);

        d.buyOneItemBycoordinates(0,4);
        assertEquals(c.getGold(),7800);

        d.buyOneItemBycoordinates(0,5);
        assertEquals(c.getGold(),7200);

        d.buyOneItemBycoordinates(0,6);
        assertEquals(c.getGold(),7000);
    }

    /**
     * This test checks if the character can buy items from the shop
     * when it has less gold than the item price.
     */
    @Test
    public void testBuyOverBudget() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(600);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);

        d.buyOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),200);

        d.buyOneItemBycoordinates(0,5);
        assertEquals(c.getGold(),200);
    }

    /**
     * This test checks if the character can buy multiple potions from the shop
     * each time when the game mode is 'survival' mode.
     * **It should be passed after we add more items in loopworld.
     */
    @Test
    public void testBuySurvivalMode() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(1000);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setGameMode(GAME_MODE.SURVIVAL);
        d.setCharacter(c);

        d.buyOneItemBycoordinates(0,6);
        assertEquals(c.getGold(),600);

        d.buyOneItemBycoordinates(0,6);
        assertEquals(c.getGold(),600);

    }

    /**
     * This test checks if the character can buy multiple potions from the shop
     * each time when the game mode is 'survival' mode.
     * **It should be passed after we add more items in loopworld.
     */
    @Test
    public void testBuyBerSerKerMode() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(1000);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setGameMode(GAME_MODE.BERSERKER);
        d.setCharacter(c);

        d.buyOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),400);

        d.buyOneItemBycoordinates(0,1);
        assertEquals(c.getGold(),400);

    }

    /**
     * This test checks if the character can sell items in the shop
     * and balance remaining after selling items is correct.
     */
    @Test
    public void testSellItems() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(0);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);
        d.addUnequippedSword();
        d.addUnequippedStake();
        
        
        d.sellOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),200);
        
        d.sellOneItemBycoordinates(1,0);
        assertEquals(c.getGold(),450);
    }

    /**
     * This test checks if the character can sell items in the shop
     * when it has no items at all in the inventory.
     */
    @Test
    public void testSellNothing() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(1000);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);

        d.sellOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),1000);

        d.sellOneItemBycoordinates(1,0);
        assertEquals(c.getGold(),1000);
    }

}
