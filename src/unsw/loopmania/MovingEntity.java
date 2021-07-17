package unsw.loopmania;


import javafx.beans.property.SimpleIntegerProperty;

/**
 * The moving entity abstract class.
 * @author Zheng Luo (z5206267)
 */
public abstract class MovingEntity extends Entity {

    /**
     * object holding position in the path
     */
    private PathPosition position;
    private double hp, damage, movingSpeed;
    private FightStrategy fightStrategy;
    


    /**
     * Create a moving entity which moves up and down the path in position
     * @param position represents the current position in the path
     */
    public MovingEntity(PathPosition position) {
        super();
        this.position = position;
    }

    /**
     * move clockwise through the path
     */
    public void moveDownPath() {
        position.moveDownPath();
    }

    /**
     * move anticlockwise through the path
     */
    public void moveUpPath() {
        position.moveUpPath();
    }

    public SimpleIntegerProperty x() {
        return position.getX();
    }

    public SimpleIntegerProperty y() {
        return position.getY();
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    // I used this. here, if not consist with format, plz change.
    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getHp() {
        return this.hp;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setMovingSpeed(double speed) {
        this.movingSpeed = speed;
    }

    public double getMovingSpeed() {
        return this.movingSpeed;
    }

    public PathPosition getPathPosition() {
        return this.position;
    }

    /**
     * Set fight strategy for MovingEntity
     */    
    public void setFightStrategy(FightStrategy fStrategy){
        this.fightStrategy = fStrategy;
    }

    /**
     * Get fight strategy for current entity.
     * @return fightStrategy as FightStrategy type.
     */
    public FightStrategy getFightStrategy() {
        return this.fightStrategy;
    }

    /**
     * Will do basic damage to an movingEntity
     */    
    public void attack(double initialDamage, MovingEntity entity) {
        fightStrategy.attack(initialDamage, entity);
    }

}
