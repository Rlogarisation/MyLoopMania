package unsw.loopmania;

import java.util.Random;

/**
 * Public class for enemy type Vampire, Written by Zheng Luo.
 */
public class Vampire extends MovingEntity implements Enemy {
    final double initialHp = 10;
    final double initialDamage = 10;
    final double initialMovingSpeed = 2;
    final double battleRadius = 3;
    final double supportRadius = 3;
    final double CritDamageMulti = 5;

    // There is 30% chance of triggering a critical bite.
    double chanceOfEffect = 0.3;

    

    public Vampire(PathPosition position) {
        // Set vampire's position.
        super(position);
        // Set vampire's hp, damage and moving speed.
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        // this.setBattleRadius(battleRadius);
        // this.setSupportRadius(supportRadius);
    }

    public void move() {

    }

    /**
     * The function can be used when setting different level of games.
     * Current default chance of 30% is used for standard mode.
     * ***Please set chanceOfEffect(double chance (0 - 1)) in the beginning of game*** 
     */
    public void setChanceOfEffect(double chance) {
        this.chanceOfEffect = chance;
    }

    public double getChanceOfEffect() {
        return this.chanceOfEffect;
    }

    /**
     *  A critical bite (which has a random chance of occurring) 
     *  from a vampire causes random additional damage 
     *  with every vampire attack, 
     *  for a random number of vampire attacks
     * 
     *  In vampire case, only vampire's damage increased
     *  no effect to the character at the moment.
     */
    public MovingEntity applyEffect(Character character) {
        // Random next double will generate a number between 0 to 1 as double,
        // and times with a multiplier, which can be changed at anytime.
        double addtionalDamage = (new Random()).nextDouble() * CritDamageMulti;
        double totalDamage = this.getDamage() + addtionalDamage;
        this.setDamage(totalDamage);

        return character;
    }

    /**
     * The chance generator function takes in a value between 0 to 1.0 as double,
     * which is the chance of selecting, 
     * e.g: there is 30% of selecting if you enter 0.3.
     * @param chance between 0 to 1 as percentage.
     * @return ture if seleted else return false as boolean.
     */
    public boolean chanceGenerator(double chance) {
        double chanceOfCriticalBite = (new Random()).nextDouble();
        if (chanceOfCriticalBite <= chance) {
            return true;
        }
        else {
            return false;
        }
    }
}
