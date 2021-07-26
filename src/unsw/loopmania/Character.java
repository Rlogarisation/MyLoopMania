package unsw.loopmania;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * represents the main character in the backend of the game world
 * @author Zheng Luo (z5206267)
 */
public class Character extends MovingEntity {
    final double initialHp = 100;
    final double initialDamage = 10;
    final double initialMovingSpeed = 2;
    final double initialXp = 0; 
    final double initialGold = 0; 
    final int initialDoggieCoin = 0;
    final double initialArmour = 0;
    
    private boolean hasHelmet = false;
    private boolean hasShield = false;
    private boolean hasArmour = false;

    private double xp, gold, armour; 
    private int doggieCoin;
    private int towerDamage;
    private boolean campfireInRange;
    private int cycleCount;


    public Character(PathPosition position) {
        super(position);
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setGold(initialGold);
        this.setDoggieCoin(initialDoggieCoin);
        this.setMovingSpeed(initialMovingSpeed);
        this.setFightStrategy(new BasicFightStrategy());
        this.towerDamage = 0;
        this.campfireInRange = false;

        this.hasHelmet = false;
        this.hasShield = false;
        this.hasArmour = false;
    }

    /**
     * Move function will move the character 1 unit in clockwise direction.
     */
    public void move() {
        this.moveDownPath();
    }


    /**
     * Add certain amount of xp as increment.
     * @param xpIncrement the amount of xp gained during each battle as double.
     */
    public void addXp(double xpIncrement) {
        this.xp += xpIncrement;
    }

    /**
     * Get the Xp of current character.
     * @return Xp of current character as double.
     */
    public double getXp() {
       return this.xp; 
    }

    /**
     * Add certain amount of gold as increment.
     * @param goldIncrement the amount of gold gained during each battle as double.
     */
    public void addGold(double goldIncrement) {
        this.gold += goldIncrement;
    }

    /**
     * Get the gold of current character.
     * @return gold of current character as double.
     */
    public double getGold() {
        return this.gold;
    }

    /**
     * Set the gold of current character.
     */
    public void setGold(double gold) {
        this.gold = gold;
    }

    /**
     * Add certain amount of doggie coin as increment.
     * @param goldIncrement the amount of doggie coin gained during battle with doggie as int.
     */
    public void addDoggieCoin(int doggieCoinIncrement) {
        this.doggieCoin += doggieCoinIncrement;
    }

    /**
     * Get the doggie coin of current character.
     * @return doggie coin of current character as int.
     */
    public int getDoggieCoin() {
        return this.doggieCoin;
    }

    /**
     * Set the doggie coin of current character.
     */
    public void setDoggieCoin(int doggieCoin) {
        this.doggieCoin = doggieCoin;
    }

    /**
     * Doggie coin price can flutuate to an extraordinary extent during to certain event.
     * calling this funtion can cause the doggie coin 
     * increase or decrease random amount within current price range.
     * for example,
     * if currentDoggieCoin is 26, then it will become [0, 52].
     * larger amount of coins will correspond to larger flutuation, vice-versa.
     * so, if coin = 0, no flutuation.
     * this function will not cause doggie coin price fall below 0.
     */
    public void flutuateDoggieCoinPrice() {
        int currentPrice = this.getDoggieCoin();
        this.setDoggieCoin(new Random().nextInt(currentPrice * 2));
    }

    /**
     * Doggie coin price can increase to an extraordinary extent when Elon Maske has created.
     * calling this funtion can cause the doggie coin 
     * increase random amount for at least 2 times and at most 3 times more.
     * for example,
     * if currentDoggieCoin is 26, then it will become [26, 104].
     * larger amount of coins will correspond to larger flutuation, vice-versa.
     * so, if coin = 0, no flutuation.
     * this function will not cause doggie coin price fall below 0. 
     */
    public void increaseDoggieCoinDrastically() {
        int currentPrice = this.getDoggieCoin();
        this.setDoggieCoin(currentPrice + (new Random()).nextInt(currentPrice * 3));
    }

    /**
     * Add certain amount of armour as increment.
     * @param armourIncrement the amount of armour gained by equipping armour as double.
     */
    public void addTotalArmour(double armourIncrement) {
        this.armour += armourIncrement;
    }

    public void setCycleCount(int cycleCount){
        this.cycleCount = cycleCount;
    }
    
    public int getCycleCount(){
        return cycleCount;
    }
    /**
     * Get the total amount of armour for current character.
     * @return armour of current character.
     */
    public double getTotalArmour() {
        return this.armour;
    }

    /**
     * print character stats
     */
    public void printCharacterStats(){
        System.out.println("Gold: "+ gold);
        System.out.println("xp: " + xp);
        System.out.println("cycles: "+ (double)cycleCount);
        System.out.println("Current Attack: "+  this.getFightStrategy());
        System.out.println("Helmet: "+  this.hasHelmet);
        System.out.println("Shield: "+  this.hasShield);
        System.out.println("Armour: "+  this.hasArmour);
        
    }

    /**
     * Get the total damage from towers in range
     * @return total tower damage
     */
    public int getTowerDamage(){
        return this.towerDamage;
    }

    /**
     * Set the current tower damage in range
     * @param towerDamage new total trap damage
     */
    public void setTowerDamage(int towerDamage){
        this.towerDamage = towerDamage;
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
     * @param status new result for campfireInRange
     */
    public void setCampfireInRange(boolean status){
        this.campfireInRange = status;
    }

    /**
     * Check if character has achieved goals depending on chosen assumptions
     * @return has achieved goal or not
     */
    public boolean hasAchievedGoal(){
        if(xp >= 10000){
            if(cycleCount >= 80 || gold >= 10000){
                return true;
            }
        }
        return false;
    }

    /**
     * getter for hasArmour
     * @return hasArmour
     */
    public boolean getHasArmour(){
        return this.hasArmour;
    }

    /**
     * setter for hasArmour
     */
    public void setHasArmour(boolean hasArmour){
        this.hasArmour = hasArmour;
    }

    /**
     * getter for hasShield
     * @return hasShield
     */
    public boolean getHasShield(){
        return this.hasShield;
    }

    /**
     * setter for hasShield
     */
    public void setHasShield(boolean hasShield){
        this.hasShield = hasShield;
    }

    /**
     * getter for hasHelmet
     * @return hasHelmet
     */
    public boolean getHasHelmet(){
        return this.hasHelmet;
    }

    /**
     * setter for hasHelmet
     */
    public void setHasHelmet(boolean hasHelmet){
        this.hasHelmet = hasHelmet;
    }

    /**
     * check if the character wears any defense equipment
     * and calculate the damage from an enemy based on the equipments 
     * @param initialDamage
     * @return totalDamage
     */
    public double defenseApplication(double initialDamage) {
        double currentDamage = initialDamage;
        
        if (hasArmour && !hasShield) {
            currentDamage = (0.5)*initialDamage;
        } else if (hasArmour && hasShield) {
            currentDamage = (1-(0.5+0.2))*initialDamage;
        } else if (!hasArmour && hasShield) {
            currentDamage = (0.8)*initialDamage;
        }
        if (hasHelmet) {
            currentDamage = (0.8)*currentDamage;
        }
        
        return currentDamage;
    }

}
