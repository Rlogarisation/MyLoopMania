package unsw.loopmania;

import java.util.List;
import unsw.loopmania.Buildings.Building;

/**
 * Public class for enemy type Zombie
 * @author Zheng Luo (z5206267)
 */
public class Zombie extends Enemy {
    final double initialHp = 5;
    final double initialDamage = 5;
    final double initialMovingSpeed = 1;
    double battleRadius = 1;
    double supportRadius = 1;

    public Zombie(PathPosition position) {
        // Set zombie's position.
        super(position);
        // Set zombie's hp, damage and moving speed.
        this.setHp(initialHp);
        this.setDamage(initialDamage);
        this.setMovingSpeed(initialMovingSpeed);
        this.setBattleRadius(battleRadius);
        this.setSupportRadius(supportRadius);
        this.setFightStrategy(new BasicFightStrategy());
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

    public void move(List<Building> buildingList) {
        this.moveRandom();
    }

}
