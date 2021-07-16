package unsw.loopmania;

import java.util.Random;

/**
 * Public class for enemy type Vampire, Written by Zheng Luo.
 */
public class Vampire extends Enemy {
    final double initialHp = 10;
    final double initialDamage = 10;
    final double initialMovingSpeed = 2;
    double battleRadius = 3;
    double supportRadius = 3;
    double CritDamageMulti = 5;
    private boolean campfireInRange;


    public Vampire(PathPosition position) {
        // Set vampire's position.
        super(position);
        // Set vampire's hp, damage and moving speed.
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        this.setBattleRadius(battleRadius);
        this.setSupportRadius(supportRadius);
        this.campfireInRange = false;
    }

    public void setBattleRadius(double battleRadius) {
        this.battleRadius = battleRadius;
    }

    public double getBattleRadius() {
        return this.battleRadius;
    } 

    public void setSupportRadius(double supportRadius) {
        this.supportRadius = supportRadius;
    }

    public double getSupportRadius() {
        return this.supportRadius;
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
    public void applyEffect(MovingEntity character) {
        // Random next double will generate a number between 0 to 1 as double,
        // and times with a multiplier, which can be changed at anytime.
        double addtionalDamage = (new Random()).nextDouble() * CritDamageMulti;
        double totalDamage = this.getDamage() + addtionalDamage;
        this.setDamage(totalDamage);

    }

    /**
     * Get a true or false if campfire is in range
     * @return boolean campfireInRange
     */
    public boolean getCampfireInRange(){
        return this.campfireInRange;
    }

    /**
     * Set a true or false if campfire is in range
     * @param yesNo new result for campfireInRange
     */
    public void setCampfireInRange(boolean yesNo){
        this.campfireInRange = yesNo;
    }

    
}
