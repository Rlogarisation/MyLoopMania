package unsw.loopmania;

/**
 * Public class for enemy type Doggie
 * "Wow much coin how money so crypto plz mine v rich very currency"
 * A special boss which spawns the DoggieCoin upon defeat, 
 * which randomly fluctuates in sellable price to an extraordinary extent. 
 * It has high health and can stun the character, 
 * which prevents the character from making an attack temporarily. 
 * The battle and support radii are the same as for slugs
 * @author Zheng Luo (z5206267)
 */
public class Doggie extends Enemy {
    final double initialHp = 40;
    final double initialDamage = 5;
    final double initialMovingSpeed = 1;
    double battleRadius = 1;
    double supportRadius = 1;

    public Doggie(PathPosition position) {
        super(position);
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        this.setBattleRadius(battleRadius);
        this.setSupportRadius(supportRadius);
        this.setFightStrategy(new DoggieStrategy());
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
