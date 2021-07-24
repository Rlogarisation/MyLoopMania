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
     * Specific code for the vampire movement to ensure that they will move outside of the campfire range
     * And will not walk into the range of a campfire if there is another available option
     */
    public void move(List<Building> buildingList) {
        boolean notComplete = true;
        Pair<Integer, Integer> newPositionDown;
        Pair<Integer, Integer> newPositionUp;
        newPositionDown = moveDownPathPos();
        newPositionUp = moveUpPathPos();
        boolean downPos = inRangeOfCampfire(buildingList, newPositionDown);
        boolean upPos = inRangeOfCampfire(buildingList, newPositionUp);

        //Both position are in range of campfire and vampire is currently not in range
        if (downPos && upPos && !campfireInRange){
            //do nothing
            notComplete = false;
        } 
        //Only downPos is in range and not upPos
        else if(downPos && !upPos){
            moveUpPath();
            notComplete = false;
        } 
        //Only upPos is in range and not downPos
        else if(upPos && !downPos){
            moveDownPath();
            notComplete = false;
        }

        //Otherwise move in a random direction
        if (notComplete){
            moveRandom();
        }
    }

    public boolean inRangeOfCampfire(List<Building> buildingList, Pair<Integer, Integer> newPos){

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
