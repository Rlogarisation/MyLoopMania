package unsw.loopmania;
import java.util.Random;

/**
 * Fighting strategy for doggie
 * Who has the special ability to stun the character.
 * @author Zheng Luo (z5206267)
 */
public class DoggieStrategy implements FightStrategy {
    double chance = 0.3;
    /**
     *  A critical bite (which has a random chance of occurring) 
     *  from a doggie causes character to stun for one round,
     *  during the stun period, 
     *  character cannot attack and will be attack by other enemies.
     */
    public void attack(double initialDamage, MovingEntity entity) {
        if (chanceGenerator(chance)) {
            if (entity instanceof Character) {
                Character myHero = (Character) entity;
                myHero.makeStun();
            }
        }
    }

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
}
