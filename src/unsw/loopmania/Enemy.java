package unsw.loopmania;

/**
 * Interface for enemy, including different enemies' behaviours 
 * Written by Zheng Luo (z5206267)
 */
public interface Enemy {

    /**
     * Move the enemy in different random direction.
     * 50% for going clockwise direction,
     * and 50% anti-clockwise direction.
     */
    public void move();


    /**
     * Set the chance of effect to be trigged,
     * the input chance is allowed between 0 to 1 as percentage.
     * The function can be used when setting different level of games.
     * Current default chance of 30% is used for standard mode.
     * ***Please set chanceOfEffect(double chance (0 - 1)) in the beginning of game*** 
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
     * Set the battle radius, 
     * the radius has been initialised in each enemy function for default level,
     * However please initialise again in controller for different difficulty.
     * @param battleRadius as double.
     */
    public void setBattleRadius(double battleRadius);

    /**
     * Get the battle radius for current enemy.
     * @return battle radius in terms of double.
     */
    public double getBattleRadius();

    /**
     * Set the support radius, 
     * the radius has been initialised in each enemy function for default level,
     * However please initialise again in controller for different difficulty.
     * @param supportRadius as double.
     */
    public void setSupportRadius(double supportRadius);

    /**
     * Get the support radius for current enemy.
     * @return support radius in terms of double.
     */
    public double getSupportRadius();

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
