package unsw.loopmania;


/**
 * Public class for enemy type Zombie, Written by Zheng Luo.
 */
public class Zombie extends Enemy {
    final double initialHp = 5;
    final double initialDamage = 5;
    final double initialMovingSpeed = 1;
    double battleRadius = 1;
    double supportRadius = 1;

    public Zombie(PathPosition position) {
        // Set zombie's position.
        super(position);
        // Set zombie's hp, damage and moving speed.
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        this.setBattleRadius(battleRadius);
        this.setSupportRadius(supportRadius);
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
     * A critical bite from a zombie against an allied soldier 
     * (which has a random chance of occurring) will 
     * transform the allied soldier into a zombie, 
     * which will then proceed to fight against the Character until it is killed.
     * 
     * ***Please check input character isInstance of Ally && ***
     * ***chanceGenerator before using***
     */
    public MovingEntity applyEffect(Character character) {
        // Delete current character.
        // Add one zombie.
        // Then return zombie.
        return character;
    }
}
