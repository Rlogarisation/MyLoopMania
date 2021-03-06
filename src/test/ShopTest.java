package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.Buildings.HeroCastle;
import unsw.loopmania.LoopManiaWorld.GAME_MODE;
import unsw.loopmania.RareItems.TreeStump;

/**
 * this test file tests shop basic functions(buy and sell)
 * @author Kihwan Baek
 */
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

        // get a sword from the shop
        d.buyOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),9600);

        // get a stake from the shop
        d.buyOneItemBycoordinates(0,1);
        assertEquals(c.getGold(),9100);

        // get a staff from the shop
        d.buyOneItemBycoordinates(0,2);
        assertEquals(c.getGold(),8600);
        
        // get an armour from the shop
        d.buyOneItemBycoordinates(0,3);
        assertEquals(c.getGold(),8300);

        // get a shield from the shop
        d.buyOneItemBycoordinates(0,4);
        assertEquals(c.getGold(),7800);

        // get a helmet from the shop
        d.buyOneItemBycoordinates(0,5);
        assertEquals(c.getGold(),7200);

        // get a health potion from the shop
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

        // get a sword from the shop
        d.buyOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),200);

        // check if the character can get an shield (price:500)
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

        // set survival mode for the game
        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setGameMode(GAME_MODE.SURVIVAL);
        d.setCharacter(c);

        // get a health potion from the shop
        d.buyOneItemBycoordinates(0,6);
        assertEquals(c.getGold(),600);

        // get a health potion from the shop 
        // -> it is failed because the game mode is 'survival'
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

        // set berserker mode for the game
        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setGameMode(GAME_MODE.BERSERKER);
        d.setCharacter(c);

        // get a sword from the shop 
        d.buyOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),400);

        // get a stake from the shop
        // -> it is failed because the game mode is 'berserker'
        d.buyOneItemBycoordinates(0,1);
        assertEquals(c.getGold(),400);

    }

    /**
     * This test checks if the character can sell items in the shop
     * and balance remaining after selling items is correct.
     */
    @Test
    public void testSellItemsByCooordinates() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(0);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);
        d.addUnequippedSword();
        d.addUnequippedStake();
        
        // sell a sword at the shop -> price 400 / 2 = 200
        d.sellOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),200);
        
        // sell a stake at the shop -> price 500 / 2 = 250
        d.sellOneItemBycoordinates(1,0);
        assertEquals(c.getGold(),450);
    
        
        d.addUnequippedHealthPotion();
        d.addUnequippedTheOneRing();

        // sell a health potion at the shop -> price 200 / 2 = 100
        d.sellOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),550);

        // sell 'the one ring' at the shop -> price 1500 / 2 = 750
        d.sellOneItemBycoordinates(1,0);
        assertEquals(c.getGold(),1300);

    }

    /**
     * This test checks if the character can sell items in the shop
     * when it has no items at all in the inventory.
     */
    @Test
    public void testSellNothingByCoordinates() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(1000);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);

        // sell nothing at the shop -> price = 0
        d.sellOneItemBycoordinates(0,0);
        assertEquals(c.getGold(),1000);

        // sell nothing at the shop -> price = 0
        d.sellOneItemBycoordinates(1,0);
        assertEquals(c.getGold(),1000);
    }

    /**
     * This test checks if the character can sell items in the shop
     * and balance remaining after selling items is correct.
     */
    @Test
    public void testSellByItem() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(1000);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);

        d.addUnequippedSword();
        d.addUnequippedArmour();
        d.addUnequippedShield();
        d.addUnequippedHelmet();
        d.addUnequippedHealthPotion();

        Entity stump = new TreeStump(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));

        Entity sword = d.getUnequippedInventoryItemEntityByCoordinates(0,0);
        Entity armour = d.getUnequippedInventoryItemEntityByCoordinates(1,0);
        Entity shield = d.getUnequippedInventoryItemEntityByCoordinates(2,0);
        Entity helmet = d.getUnequippedInventoryItemEntityByCoordinates(3,0);
        Entity potion = d.getUnequippedInventoryItemEntityByCoordinates(0,1);

        // sell nothing at the shop -> price = 0
        Boolean isSuccess = d.sellOneItemByItem(null);
        assertFalse(isSuccess);
        assertEquals(c.getGold(),1000);

        // sell item which is not in inventory at the shop -> price = 0
        isSuccess = d.sellOneItemByItem(stump);
        assertFalse(isSuccess);
        assertEquals(c.getGold(),1000);


        // sell a swrod at the shop -> price = 400/2 = 200
        isSuccess = d.sellOneItemByItem(sword);
        assertTrue(isSuccess);
        assertEquals(c.getGold(),1200);
        
        // sell an armour at the shop -> price = 300/2 = 150
        isSuccess = d.sellOneItemByItem(armour);
        assertTrue(isSuccess);
        assertEquals(c.getGold(),1350);

        // sell a shield at the shop -> price = 500/2 = 250
        isSuccess = d.sellOneItemByItem(shield);
        assertTrue(isSuccess);
        assertEquals(c.getGold(),1600);

        // sell a helmet at the shop -> price = 600/2 = 300
        isSuccess = d.sellOneItemByItem(helmet);
        assertTrue(isSuccess);
        assertEquals(c.getGold(),1900);

        // sell swrod at the shop -> price = 200/2 = 100
        isSuccess = d.sellOneItemByItem(potion);
        assertTrue(isSuccess);
        assertEquals(c.getGold(),2000);
    }

    /**
     * This test checks if the character can sell doggie coin in the shop
     * and balance remaining after selling items is correct. 
     */
    @Test
    public void testSellDoggieCoin() {
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        c.setGold(0);

        d.setHeroCastle(new HeroCastle(new SimpleIntegerProperty(0),new SimpleIntegerProperty(0)));
        d.setCharacter(c);

        // sell nothing at the shop -> price = 0
        Boolean isSuccess = d.sellOneDoggieCoin();
        assertFalse(isSuccess);
        assertEquals(c.getGold(),0);

        // add 3 doggie coins
        d.getCharacter().addDoggieCoin(3);
        d.getCharacter().setDoggieCoinPrice(100);
        
        // sell a doggie coin at the shop -> price = 100
        isSuccess = d.sellOneDoggieCoin();
        assertTrue(isSuccess);
        assertEquals(c.getGold(),100);

        d.getCharacter().setDoggieCoinPrice(450);
        
        // sell a doggie coin at the shop -> price = 450
        isSuccess = d.sellOneDoggieCoin();
        assertTrue(isSuccess);
        assertEquals(c.getGold(),550);

        d.getCharacter().setDoggieCoinPrice(10);
        
        // sell a doggie coin at the shop -> price = 10
        isSuccess = d.sellOneDoggieCoin();
        assertTrue(isSuccess);
        assertEquals(c.getGold(),560);

        // sell nothing at the shop -> price = 0
        isSuccess = d.sellOneDoggieCoin();
        assertFalse(isSuccess);
        assertEquals(c.getGold(),560);



    }
}
