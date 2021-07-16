package unsw.loopmania;

public class Ally extends MovingEntity{
    /**
     * Ally class baseHealth = 20 and baseAttack = 5
     */

    final private double baseHealth = 20;
    final private double baseAttack = 5;
    final private double initialMovingSpeed = 2;
    

    public Ally(PathPosition position) {
        super(position);
        setDamage(baseAttack);
        setHp(baseHealth);
        setMovingSpeed(initialMovingSpeed);
        setFightStrategy(new BasicFightStrategy());
    }
    
    


    
    
}
