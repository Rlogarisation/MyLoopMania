package unsw.loopmania;

public interface FightStrategy {
    /**
     * Attacks an entity differently based on strategy
     * @param initialDamage based on entity base damage
     * @param entity which entity is being attack
     */
    public void attack(double initialDamage, MovingEntity entity);
}
