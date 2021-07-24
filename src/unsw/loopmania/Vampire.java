package unsw.loopmania;

import java.util.List;
import org.javatuples.Pair;
import unsw.loopmania.Buildings.*;

/**
 * Public class for enemy type Vampire
 * @author Zheng Luo (z5206267)
 */
public class Vampire extends Enemy {
    final double initialHp = 10;
    final double initialDamage = 10;
    final double initialMovingSpeed = 2;
    double battleRadius = 3;
    double supportRadius = 3;
    private boolean campfireInRange;


    public Vampire(PathPosition position) {
        // Set vampire's position.
        super(position);
        // Set vampire's hp, damage and moving speed.
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        this.setBattleRadius(battleRadius);
        this.setSupportRadius(supportRadius);
        this.setFightStrategy(new VampireStrategy());
        this.campfireInRange = false;
    }

    public void setBattleRadius(double battleRadius) {
        this.battleRadius = battleRadius;
    }

    public double getBattleRadius() {
        return this.battleRadius;
    } 

    public void setSupportRadius(double supportRadius) {
        this.supportRadius = supportRadius;
    }

    public double getSupportRadius() {
        return this.supportRadius;
    }

    /**
     * Get a true or false if campfire is in range
     * @return boolean campfireInRange
     */
    public boolean getCampfireInRange(){
        return this.campfireInRange;
    }

    /**
     * Set a true or false if campfire is in range
     * @param yesNo new result for campfireInRange
     */
    public void setCampfireInRange(boolean yesNo){
        this.campfireInRange = yesNo;
    }

    /**
     * Move the enemy in different random direction.
     * 50% for going clockwise direction,
     * and 50% anti-clockwise direction.
     */
    public void move(List<Building> buildingList) {
        boolean notComplete = true;
        if (campfireInRange){
            Pair<Integer, Integer> newPositionDown;
            Pair<Integer, Integer> newPositionUp;
            //check the down path
            newPositionDown = moveDownPathPos();
            if (!samePositionAsCampfire(buildingList, newPositionDown) && notComplete){
                moveDownPath();
                notComplete = false;
            }

            //check the up path
            newPositionUp = moveUpPathPos();
            if (!samePositionAsCampfire(buildingList, newPositionUp) && notComplete){
                moveUpPath();
                notComplete = false;
            }
        }
        
        //No options have occured
        if (notComplete){
            this.moveRandom();
        }
    }

    public boolean samePositionAsCampfire(List<Building> buildingList, Pair<Integer, Integer> newPos){

        for (Building b: buildingList){
            if (b instanceof Campfire){
                if (Math.pow((newPos.getValue0()-b.getX()), 2) +  Math.pow((newPos.getValue1()-b.getY()), 2) <= Math.pow(1, 2)){
                    return true;
                }
            }
        }

        return false;
    }

    
}
