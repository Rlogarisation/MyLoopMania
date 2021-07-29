package test;



import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.javatuples.Pair;

import unsw.loopmania.*;
import unsw.loopmania.Character;


public class CharacterWeaponTest {
    @Test
    public void characterStrategiesTest(){
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

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
    
        // slug: hp = 20 and damage = 2, radius = 1.
        Slug slug1 = new Slug(position00);
        currentWorld.addEnemy(slug1);
        slug1.setHp(20);
        slug1.setDamage(2);

        //Sword: +6 damage, total damage = 10+6 = 16
        character.setFightStrategy(new SwordStrategy());
        List<Enemy> defeatedEnemies = currentWorld.runBattles();
        assert(slug1.getHp() == -12);
        assert(defeatedEnemies.contains(slug1));

        //Restore characterHP to avoid dying
        character.setHp(100);
        // slug: hp = 20 and damage = 2, radius = 1.
        Slug slug2 = new Slug(position00);
        currentWorld.addEnemy(slug2);
        slug2.setHp(20);
        slug2.setDamage(2);

        //Stake: +4 damage, total damage = 10+4 = 14
        character.setFightStrategy(new StakeStrategy());
        defeatedEnemies = currentWorld.runBattles();
        assert(slug2.getHp() == -8);
        assert(defeatedEnemies.contains(slug2));

        //Restore characterHP to avoid dying
        character.setHp(100);
        // slug: hp = 20 and damage = 2, radius = 1.
        Slug slug3 = new Slug(position00);
        currentWorld.addEnemy(slug3);
        slug3.setHp(20);
        slug3.setDamage(2);

        //Staff: +3 damage, total damage = 10+3 = 13
        character.setFightStrategy(new StaffStrategy());
        defeatedEnemies = currentWorld.runBattles();
        assert(slug3.getHp() == -6);
        assert(defeatedEnemies.contains(slug3));
    }

    @Test
    public void StakeStrategyTest(){
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

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
    
        // vampire: hp = 20 and damage = 10, radius = 3.
        Vampire vampire = new Vampire(position00);
        vampire.setHp(20);
        currentWorld.addEnemy(vampire);
        //Stake: +4 damage, total damage = 10+4 = 14
        //To vampire +8 damage, total damage = 10+8 = 18
        character.setFightStrategy(new StakeStrategy());
        List<Enemy> defeatedEnemies = currentWorld.runBattles();
        assert(vampire.getHp() == -16);
        assert(defeatedEnemies.contains(vampire));
    }

    /**
     * check if the defense equipments actually defense enemies' attack
     */
    @Test
    public void DefenseStrategyTest(){
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

        // character: hp = 100, damage = 10.
        Character character = new Character(position00);
        currentWorld.setCharacter(character);
    
        // slug: hp = 20 and damage = 5, radius = 1.
        Slug slug = new Slug(position00);
        slug.setHp(100);
        slug.setDamage(5);
        character.setFightStrategy(new BasicFightStrategy());

        //Sword: +6 damage, total damage = 10+6 = 16
        character.setFightStrategy(new SwordStrategy());
        character.setHasArmour(true);
        slug.attack(slug.getDamage(),character);
        assert(character.getHp() == 97.5);

        character.setHasShield(true);
        character.setHp(100);
        slug.attack(slug.getDamage(),character);
        System.out.println(character.getHp());
        assert(character.getHp() == 98.25);

        character.setHasArmour(false);
        character.setHp(100);
        slug.attack(slug.getDamage(),character);
        assert(character.getHp() == 95.75);

        character.setHasShield(false);
        character.setHasHelmet(true);
        character.setHp(100);
        slug.attack(slug.getDamage(),character);
        assert(character.getHp() == 96);

    }
}
