package unsw.loopmania;

public class SwordStrategy implements FightStrategy{
    private final int damage = 6;
    public void attack(double initialDamage, Enemy enemy) {
        double currentHp = enemy.getHp();
        double totalDamage = initialDamage+(damage*2);
        enemy.setHp(currentHp-totalDamage);
    }
    
}
