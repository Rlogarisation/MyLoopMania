package unsw.loopmania;



/**
 * Public class for enemy type Vampire, Written by Zheng Luo.
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

    
}
