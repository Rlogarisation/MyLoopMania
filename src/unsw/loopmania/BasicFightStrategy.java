package unsw.loopmania;

public class BasicFightStrategy implements FightStrategy {

    /**
     * Does initial damage value
     */
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        entity.setHp(currentHp-initialDamage);
        
    }


    
}
