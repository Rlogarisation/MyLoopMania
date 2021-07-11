package unsw.loopmania;

/**
 * Public class for enemy type Zombie, Written by Zheng Luo.
 */
public class Zombie extends MovingEntity implements Enemy {
    double chanceOfEffect = 0;

    public Zombie(PathPosition position) {
        super(position);
    }

    public void move() {

    }

    public void setChanceOfEffect(double chance) {

    }

    public double getChanceOfEffect() {
        return 0.0;
    }

    public MovingEntity applyEffect(Character character) {
        return character;
    }
}
