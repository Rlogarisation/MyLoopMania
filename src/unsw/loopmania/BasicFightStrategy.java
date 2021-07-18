package unsw.loopmania;

public class BasicFightStrategy implements FightStrategy {

    /**
     * if the entity is the character, damage received varies depending on
     * whether or not the defense equipment is worn
     */
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        double currentDamage = initialDamage;
        if (entity instanceof Character) {
            currentDamage = ((Character) entity).defenseApplication(currentDamage);            
        }
        entity.setHp(currentHp - currentDamage);        
    }
}
