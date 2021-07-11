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

    double chanceOfEffect = 0;

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

    public void setChanceOfEffect(double chance) {
        this.chanceOfEffect = chance;
    }

    public double getChanceOfEffect() {
        return this.chanceOfEffect;
    }

    public MovingEntity applyEffect(Character character) {
        return character;
    }
}
