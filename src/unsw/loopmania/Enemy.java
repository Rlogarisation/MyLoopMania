package unsw.loopmania;

import java.util.Random;

/**
 * Abstract class for enemy, including different enemies' behaviours 
 * Written by Zheng Luo (z5206267)
 */
public abstract class Enemy extends MovingEntity{
    /**
     * There is 30% chance of triggering a critical bite.
     */
    double chanceOfEffect = 0.3;
    private boolean isTranced;
    public Enemy(PathPosition position) {
        super(position);
    }

    /**
     * Move the enemy in different random direction.
     * 50% for going clockwise direction,
     * and 50% anti-clockwise direction.
     */
    public void move() {
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

    public void setIsTranced(boolean isEnemyTranced) {
        isTranced = isEnemyTranced;
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

    /**
     * Apply the special effect to input character,
     * and change its properties based on the effect.
     * @param character
     */
    public abstract void applyEffect(Character character);

    /**
     * The chance generator function takes in a value between 0 to 1.0 as double,
     * which is the chance of selecting, 
     * e.g: there is 30% of selecting if you enter 0.3.
     * @param chance between 0 to 1 as percentage.
     * @return ture if seleted else return false as boolean.
     */
    public boolean chanceGenerator(double chance) {
        double chanceOfCriticalBite = (new Random()).nextDouble();
        if (chanceOfCriticalBite <= chance) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Enemy will attack the input character or ally until either is dead.
     * @param character who is fighting with current enemy.
     */
    public void attack(Character character) {
        while (character.getHp() > 0 && this.getHp() > 0) {
            if (chanceGenerator(chanceOfEffect)) {
                this.applyEffect(character);
            }
            
            this.setHp(this.getHp() - character.getDamage());
            // How should I apply armour in here?
            character.setHp(character.getHp() - this.getDamage());
        }
    }
    
}
