package unsw.loopmania;

public class BasicFightStrategy implements FightStrategy {

    /**
     * Does initial damage value
     */
    public void attack(double initialDamage, Enemy enemy) {
        double currentHp = enemy.getHp();
        enemy.setHp(currentHp-initialDamage);
        
    }


    
}
