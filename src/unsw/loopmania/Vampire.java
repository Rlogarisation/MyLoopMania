package unsw.loopmania;

/**
 * Public class for enemy type Vampire, Written by Zheng Luo.
 */
public class Vampire extends MovingEntity implements Enemy {
    double chanceOfEffect = 0;

    public Vampire(PathPosition position) {
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
