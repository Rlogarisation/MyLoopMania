package unsw.loopmania;

/**
 * Public class for enemy type Slug
 * @author Zheng Luo (z5206267)
 */
public class Slug extends Enemy{
    final double initialHp = 5;
    final double initialDamage = 2;
    final double initialMovingSpeed = 1;
    double battleRadius = 1;
    double supportRadius = 1;


    public Slug(PathPosition position) {
        // Set slug's position.
        super(position);
        // Set slug's hp, damage and moving speed.
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        this.setBattleRadius(battleRadius);
        this.setSupportRadius(supportRadius);
        this.setFightStrategy(new BasicFightStrategy());
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


}
