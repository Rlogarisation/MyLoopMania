package unsw.loopmania;

/**
 * Interface for enemy, including different enemies' behaviours 
 * Written by Zheng Luo (z5206267)
 */
public interface Enemy {

    /**
     * Move the enemy in different random direction.
     */
    public void move();


    /**
     * Set the chance of effect to be trigged,
     * the input chance is allowed between 0 to 1 as percentage.
     * @param chance in double.
     */
    public void setChanceOfEffect(double chance);

    /**
     * get the chance of effect, 
     * which is the possibility of triggering its special ability.
     * 
     * @return the possibility in the range of 0 to 1.
     */
    public double getChanceOfEffect();


    /**
     * Apply the special effect to input character,
     * and change its properties based on the effect.
     * @param character
     * @return the affected character/ enemy as type MovingEntity,
     * since zombie can transform character into enemy, 
     * hence the return type become MovingEntity.
     */
    public MovingEntity applyEffect(Character character);

    
}
