package unsw.loopmania;

/**
 * Public class for enemy type Slug, Written by Zheng Luo.
 */
public class Slug extends MovingEntity implements Enemy{
    final double initialHp = 5;
    final double initialDamage = 2;
    final double initialMovingSpeed = 1;
    final double battleRadius = 1;
    final double supportRadius = 1;

    double chanceOfEffect = 0.3;

    public Slug(PathPosition position) {
        // Set slug's position.
        super(position);
        // Set slug's hp, damage and moving speed.
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
     * Slug has no effect.
     */
    public MovingEntity applyEffect(Character character) {
        return character;
    }
}
