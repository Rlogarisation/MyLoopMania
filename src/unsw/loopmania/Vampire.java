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
     */
    public MovingEntity applyEffect(Character character) {

        // double chanceOfCriticalBite = (new Random()).nextDouble();
        // if (chanceOfCriticalBite <= chanceOfEffect) {

        // }

        return character;
    }
}
