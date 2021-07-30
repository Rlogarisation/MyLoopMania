package test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;
import unsw.loopmania.LoopManiaWorld.GAME_MODE;
import unsw.loopmania.RareItems.AndurilSword;
import unsw.loopmania.RareItems.ConfusingRareItem;
import unsw.loopmania.RareItems.TheOneRing;
import unsw.loopmania.RareItems.TreeStump;

public class ConfusingModeTest {
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        SimpleIntegerProperty y = new SimpleIntegerProperty(0);
        LoopManiaWorld d = new LoopManiaWorld(1, 1, new ArrayList<>());
        List<Pair<Integer, Integer>> orderedPath = new ArrayList<Pair<Integer,Integer>>();
        
    @Test
    public void testMode(){
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);
        // set Confusing mode for the game
        d.setGameMode(GAME_MODE.CONFUSING);
        assertTrue(d.getGameMode() == GAME_MODE.CONFUSING);

        
    }

    @Test
    public void testInventoryConfusingRareItem(){
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);
        // set Confusing mode for the game
        d.setGameMode(GAME_MODE.CONFUSING);

        //Test Confusing rare item added to unequippedInventory
        //The One Ring Confusing Rare Item
        StaticEntity cItem = d.addUnequippedTheOneRing();
        assertTrue(cItem instanceof ConfusingRareItem);
        assertTrue(d.getUnequippedInventoryItemEntityByCoordinates(0, 0) == cItem);
        //Test initial item is one ring
        ConfusingRareItem cItemOneRing = (ConfusingRareItem) cItem;
        assertTrue(cItemOneRing.getInitialRareItem() instanceof TheOneRing);

        //Test new item is a rare item type
        assertTrue(cItemOneRing.getNewRareItem() instanceof TheOneRing || 
                    cItemOneRing.getNewRareItem() instanceof AndurilSword ||
                    cItemOneRing.getNewRareItem() instanceof TreeStump);
        //Clean up
        d.removeUnequippedInventoryItemByCoordinates(0, 0);

        //Anduril Sworld Confusing Rare Item
        StaticEntity cItem1 = d.addUnequippedAndurilSword();
        assertTrue(cItem1 instanceof ConfusingRareItem);
        assertTrue(d.getUnequippedInventoryItemEntityByCoordinates(0, 0) == cItem1);
        //Test intial item is anduril sword
        ConfusingRareItem cItemAndurilSword = (ConfusingRareItem) cItem1;
        assertTrue(cItemAndurilSword.getInitialRareItem() instanceof AndurilSword);
        //Test new item is a rare item type
        assertTrue(cItemAndurilSword.getNewRareItem() instanceof TheOneRing || 
                    cItemAndurilSword.getNewRareItem() instanceof AndurilSword ||
                    cItemAndurilSword.getNewRareItem() instanceof TreeStump);
        //Clean up
        d.removeUnequippedInventoryItemByCoordinates(0, 0);

        //Tree Stump Confusing Rare Item
        StaticEntity cItem2 = d.addUnequippedTreeStump();
        assertTrue(cItem2 instanceof ConfusingRareItem);
        assertTrue(d.getUnequippedInventoryItemEntityByCoordinates(0, 0) == cItem2);
        //Test intial item is tree stump
        ConfusingRareItem cItemTreeStump= (ConfusingRareItem) cItem2;
        assertTrue(cItemTreeStump.getInitialRareItem() instanceof TreeStump);

        //Test new item is a rare item type
        assertTrue(cItemTreeStump.getNewRareItem() instanceof TheOneRing || 
                    cItemTreeStump.getNewRareItem() instanceof AndurilSword ||
                    cItemTreeStump.getNewRareItem() instanceof TreeStump);
        //Clean up
        d.removeUnequippedInventoryItemByCoordinates(0, 0);
    }


    @Test
    public void testEquipConfusingRareItem(){
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);
        // set Confusing mode for the game
        d.setGameMode(GAME_MODE.CONFUSING);

        //Create unequipped andurilsword confusing rareitem
        StaticEntity cItem = d.addUnequippedAndurilSword();
        ConfusingRareItem cItemAndurilSword = (ConfusingRareItem) cItem;
        //Set tree stump as second rare item
        cItemAndurilSword.setNewRareItem(new TreeStump(x,y));
        //Equip Tree stump onto character
        d.equipConfusingRareItem(cItemAndurilSword, cItemAndurilSword.getNewRareItem());
        //Assert character has stump
        assertTrue(c.getHasStump());
        

        //Create unequipped Tree Stump
        StaticEntity cItem1 = d.addUnequippedTreeStump();
        ConfusingRareItem cItemTreeStump = (ConfusingRareItem) cItem1;
        //Set Anduril sword as second rare item
        cItemTreeStump.setNewRareItem(new AndurilSword(x,y));
        //Equip sword onto character
        d.equipConfusingRareItem(cItemTreeStump, cItemTreeStump.getNewRareItem());
        //Assert character has sword
        assertTrue(c.getFightStrategy() instanceof AndurilStrategy);


        //Create unequipped One Ring
        StaticEntity cItem2 = d.addUnequippedTheOneRing();
        ConfusingRareItem cItemOneRing = (ConfusingRareItem) cItem2;
        assert(cItemOneRing.hasOneRing());
        //Set Anduril sword as second rare item
        cItemOneRing.setNewRareItem(new AndurilSword(x,y));
        //Equip sword onto character
         d.equipConfusingRareItem(cItemOneRing, cItemOneRing.getNewRareItem());
        //Assert character has sword
         assertTrue(c.getFightStrategy() instanceof AndurilStrategy);

         //Create unequipped One Ring
        StaticEntity cItem3 = d.addUnequippedTheOneRing();
        ConfusingRareItem cItemOneRing1 = (ConfusingRareItem) cItem3;
        assert(cItemOneRing1.hasOneRing());
        //Set tree stump as second rare item
        cItemOneRing1.setNewRareItem(new TreeStump(x,y));
        //Equip Tree stump onto character
        d.equipConfusingRareItem(cItemOneRing1, cItemOneRing1.getNewRareItem());
        //Assert character has stump
        assertTrue(c.getHasStump());

        //Create unequipped andurilsword confusing rareitem
        StaticEntity cItem4 = d.addUnequippedAndurilSword();
        ConfusingRareItem cItemAndurilSword1 = (ConfusingRareItem) cItem4;
        //Set one ring as second rare item
        cItemAndurilSword1.setNewRareItem(new TheOneRing(x,y));
        //Assert has one ring
        assert(cItemAndurilSword1.hasOneRing());

        //Create unequipped Tree confusing rareitem
        StaticEntity cItem5 = d.addUnequippedTreeStump();
        ConfusingRareItem cItemTreeStump1 = (ConfusingRareItem) cItem5;
        //Set one ring as second rare item
        cItemTreeStump1.setNewRareItem(new TheOneRing(x,y));
        //Assert has one ring
        assert(cItemTreeStump1.hasOneRing());

    }

    @Test
    public void testReviveOneRing(){
        orderedPath.add(new Pair<Integer,Integer>(0,0));
        Character c = new Character(new PathPosition(0,orderedPath));
        d.setCharacter(c);
        // set Confusing mode for the game
        d.setGameMode(GAME_MODE.CONFUSING);


         //Create unequipped One Ring
         StaticEntity cItem2 = d.addUnequippedTheOneRing();
         ConfusingRareItem cItemOneRing = (ConfusingRareItem) cItem2;
         //Set Anduril sword as second rare item
         cItemOneRing.setNewRareItem(new AndurilSword(x,y));
         //Equip sword
         d.equipConfusingRareItem(cItemOneRing, cItemOneRing.getNewRareItem());
         //Set character health to zero
         c.setHp(0);
         //Attempt to revive
         d.reviveCharacter();
         System.out.println(c.getHp());
         //Assert character health is 100
         assertTrue(c.getHp() == 100);
         //Assert confusing rare item doesn't exist anymore
         assert(d.getEquippedInventoryItemEntityByCoordinates(0, 0) == null);
 
          //Create unequipped One Ring
         StaticEntity cItem3 = d.addUnequippedTheOneRing();
         ConfusingRareItem cItemOneRing1 = (ConfusingRareItem) cItem3;
         //Set tree stump as second rare item
         cItemOneRing1.setNewRareItem(new TreeStump(x,y));
         //Equip tree stump
         d.equipConfusingRareItem(cItemOneRing1, cItemOneRing1.getNewRareItem());
         //Assert character has stump
         assertTrue(c.getHasStump());
         //Set character health to zero
         c.setHp(0);
         //Attempt to revive
         d.reviveCharacter();

         //Assert character health is 100
         assertTrue(c.getHp() == 100);
         //Assert confusing rare item doesn't exist anymore
         assert(d.getEquippedInventoryItemEntityByCoordinates(0, 0) == null);



    }
}
