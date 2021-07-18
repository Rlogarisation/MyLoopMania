package unsw.loopmania;
import java.util.Random;

/**
 * Fighting strategy for vampire
 * @author Zheng Luo (z5206267)
 */
public class VampireStrategy implements FightStrategy{

    double CritDamageMulti = 5;
    double chance = 0.3;
    /**
     *  A critical bite (which has a random chance of occurring) 
     *  from a vampire causes random additional damage 
     *  with every vampire attack, 
     *  for a random number of vampire attacks
     * 
     *  In vampire case, only vampire's damage increased
     *  no effect to the character at the moment.
     */
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        double totalDamage = initialDamage;
        if (chanceGenerator(chance)) {
            // Random next double will generate a number between 0 to 1 as double,
            // and times with a multiplier, which can be changed at anytime.
            double addtionalDamage = (new Random()).nextDouble() * CritDamageMulti;
            totalDamage = initialDamage + addtionalDamage;
            if (entity instanceof Character) {
                totalDamage = ((Character) entity).defenseApplication(totalDamage);            
            }
            entity.setHp(currentHp - totalDamage);
        }
        else {
            if (entity instanceof Character) {
                totalDamage = ((Character) entity).defenseApplication(totalDamage);            
            }
            entity.setHp(currentHp - totalDamage);
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
