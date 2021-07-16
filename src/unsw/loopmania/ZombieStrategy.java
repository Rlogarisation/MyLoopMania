package unsw.loopmania;
import java.util.Random;

public class ZombieStrategy implements FightStrategy {

    double chance = 0.3;
    /**
     * A critical bite from a zombie against an allied soldier 
     * (which has a random chance of occurring) will 
     * transform the allied soldier into a zombie, 
     * which will then proceed to fight against the Character until it is killed.
     * 
     * ***Please check input character isInstance of Ally && ***
     * ***chanceGenerator before using***
     */
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        if (chanceGenerator(chance)) {
            if (entity instanceof Ally) {
                // Delete current ally and create another zombie.
                
                Zombie newZombie = new Zombie(entity.getPathPosition());
            }
            else {
                entity.setHp(currentHp - initialDamage);
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
