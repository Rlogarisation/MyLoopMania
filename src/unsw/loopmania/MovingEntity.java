package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

import org.javatuples.Pair;

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

    /**
     * Get the position of the next clockwise path position
     * @return A pair of integers of the path position
     */
    public Pair<Integer, Integer> moveDownPathPos(){
        return position.moveDownPathPos();
    }

    /**
     * Get the position of the next anticlockwise path position
     * @return A pair of integers of the path position
     */
    public Pair<Integer, Integer> moveUpPathPos(){
        return position.moveUpPathPos();
    }

    /**
     * Get the X coordinate for current moving entity in front end.
     * @return x coordinate as SimpleIntegerProperty.
     */
    public SimpleIntegerProperty x() {
        return position.getX();
    }

    /**
     * Get the Y coordinate for current moving entity in front end.
     * @return y coordinate as SimpleIntegerProperty.
     */
    public SimpleIntegerProperty y() {
        return position.getY();
    }

    /**
     * Get the X coordinate for current moving entity.
     * @return x coordinate as int.
     */
    public int getX() {
        return x().get();
    }

    /**
     * Get the Y coordinate for current moving entity.
     * @return y coordinate as int.
     */
    public int getY() {
        return y().get();
    }


    /**
     * Set health of current moving entity.
     * update progressBar
     * @param hp the health as double.
     */
    public void setHp(double hp) {
        this.hp = hp;
    }

    /**
     * Get the health for current moving entity.
     * @return the health as double.
     */
    public double getHp() {
        return this.hp;
    }

    /**
     * Set the damage for current moving entity.
     * @param damage input damage as double.
     */
    public void setDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Get the damage of current moving entity.
     * @return the amount of damage will dealt to others as double.
     */
    public double getDamage() {
        return this.damage;
    }

    /**
     * Set the moving speed for current moving entity.
     * @param speed desired speed as input in double.
     */
    public void setMovingSpeed(double speed) {
        this.movingSpeed = speed;
    }

    /**
     * Get the moving speed of current moving entity.
     * @return moving speed as double.
     */
    public double getMovingSpeed() {
        return this.movingSpeed;
    }

    /**
     * Get the path position of current moving entity.
     * @return current position as PathPosition
     */
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
     * The attack function will deal basic damage to an movingEntity
     * @param initialDamage the amount of damage generated by current moving entity.
     * @param entity the entity which will be attacked.
     */    
    public void attack(double initialDamage, MovingEntity entity) {
        fightStrategy.attack(initialDamage, entity);
    }

}
