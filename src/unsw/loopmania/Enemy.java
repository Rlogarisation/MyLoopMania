package unsw.loopmania;

import java.util.Random;
import java.util.List;
import unsw.loopmania.Buildings.Building;


/**
 * Abstract class for enemy, including different enemies' behaviours 
 * Written by Zheng Luo (z5206267)
 */
public abstract class Enemy extends MovingEntity{
    /**
     * There is 30% chance of triggering a critical bite.
     */
    double chanceOfEffect = 0.3;
    private boolean isTranced = false;
    public Enemy(PathPosition position) {
        super(position);
    }

    /**
     * Enemy default movement, which moves randomly.
     * @param buildingList all nearby build list.
     */
    public void move(List<Building> buildingList) {
        this.moveRandom();
    }

    /**
     * Move the enemy in different random direction.
     * 50% for going clockwise direction,
     * and 50% anti-clockwise direction.
     */
    public final void moveRandom(){
        int directionChoice = (new Random()).nextInt(2);
        if (directionChoice == 0){
            moveUpPath();
        }
        else if (directionChoice == 1){
            moveDownPath();
        }
    }

    

    /**
     * Set the chance of effect to be trigged,
     * the input chance is allowed between 0 to 1 as percentage.
     * The function can be used when setting different level of games.
     * Current default chance of 30% is used for standard mode.
     * ***Please set chanceOfEffect(double chance (0 - 1)) in the beginning of game*** 
     * @param chance in double.
     */
    public void setChanceOfEffect(double chance) {
        this.chanceOfEffect = chance;
    }

    /**
     * get the chance of effect, 
     * which is the possibility of triggering its special ability.
     * 
     * @return the possibility in the range of 0 to 1.
     */
    public double getChanceOfEffect() {
        return this.chanceOfEffect;
    }

    /**
     * Set the tranced status of current enemy.
     * @param isEnemyTranced trance status as boolean.
     */
    public void setIsTranced(boolean isEnemyTranced) {
        this.isTranced = isEnemyTranced;
    }

    /**
     * Get the tranced status for current enemy.
     * @return tranced status as boolean.
     */
    public boolean getTrancedStatus() {
        return this.isTranced;
    }

    /**
     * Set the battle radius, 
     * the radius has been initialised in each enemy function for default level,
     * However please initialise again in controller for different difficulty.
     * @param battleRadius as double.
     */
    public abstract void setBattleRadius(double battleRadius);

    /**
     * Get the battle radius for current enemy.
     * @return battle radius in terms of double.
     */
    public abstract double getBattleRadius();

    /**
     * Set the support radius, 
     * the radius has been initialised in each enemy function for default level,
     * However please initialise again in controller for different difficulty.
     * @param supportRadius as double.
     */
    public abstract void setSupportRadius(double supportRadius);

    /**
     * Get the support radius for current enemy.
     * @return support radius in terms of double.
     */
    public abstract double getSupportRadius();

    
}
