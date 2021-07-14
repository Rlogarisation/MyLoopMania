package unsw.loopmania;
import unsw.loopmania.Stake;
public class StakeStrategy implements FightStrategy{
    private final int damage = 4;
    @Override
    public void attack(double initialDamage, Enemy enemy) {
        double currentHp = enemy.getHp();
        if(enemy instanceof Vampire){
            double totalDamage = initialDamage+(damage*2);
            enemy.setHp(currentHp-totalDamage);
        }
        else{
            double totalDamage = initialDamage+damage;
            enemy.setHp(currentHp - totalDamage);
        }
    }
    
}
