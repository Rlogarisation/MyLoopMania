package unsw.loopmania;

/**
 * Public class for enemy type Zombie, Written by Zheng Luo.
 */
public class Zombie extends MovingEntity implements Enemy {
    final double initialHp = 5;
    final double initialDamage = 5;
    final double initialMovingSpeed = 1;
    final double battleRadius = 1;
    final double supportRadius = 1;

    double chanceOfEffect = 0;

    public Zombie(PathPosition position) {
        // Set zombie's position.
        super(position);
        // Set zombie's hp, damage and moving speed.
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
