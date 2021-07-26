package unsw.loopmania;

import java.util.List;

/**
 * Public class for enemy type Elan Muske
 * An incredibly tough boss which, when appears, 
 * causes the price of DoggieCoin to increase drastically. 
 * Defeating this boss causes the price of DoggieCoin to plummet. 
 * Elan has the ability to heal other enemy NPCs. 
 * The battle and support radii are the same as for slugs
 * @author Zheng Luo (z5206267)
 */

public class ElanMuske extends Enemy {
    final double initialHp = 50;
    final double initialDamage = 10;
    final double initialMovingSpeed = 1;
    double battleRadius = 1;
    double supportRadius = 1;

    public ElanMuske(PathPosition position) {
        super(position);
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

    /**
     * Elan Musky speical effect, who has certain chance to heal all enemies by 10%.
     * this function can heal all enemies by 10%,
     * but please implement chance decision maker in battleList.
     * @param allFightingEnemy all enemies in range as list
     * @return all enemies after healed as list.
     */
    public List<Enemy> healAllEnemies(List<Enemy> allFightingEnemy) {
        for (Enemy currentEnemy: allFightingEnemy) {
            double currentHp = currentEnemy.getHp();
            currentEnemy.setHp(currentHp + currentHp * 0.1);
        }
        return allFightingEnemy;
    }

}
