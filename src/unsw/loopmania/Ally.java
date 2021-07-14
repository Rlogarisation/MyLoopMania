package unsw.loopmania;

public class Ally extends MovingEntity{
    /**
     * Ally class baseHealth = 20 and baseAttack = 5
     */

    final private double baseHealth = 20;
    final private double baseAttack = 5;
    final private double initialMovingSpeed = 2;
    private FightStrategy fightStrategy;

    public Ally(PathPosition position) {
        super(position);
        setDamage(baseAttack);
        setHp(baseHealth);
        setMovingSpeed(initialMovingSpeed);
        setFightStrategy(new BasicFightStrategy());
    }
    
    /**
     * Set fight strategy for Ally
     */    
    public void setFightStrategy(FightStrategy fStrategy){
        this.fightStrategy = fStrategy;
    }

    /**
     * Will do basic damage to an enemy
     */    
    public void attack(double initialDamage, Enemy enemy){
        fightStrategy.attack(initialDamage, enemy);
    }


    
    
}
