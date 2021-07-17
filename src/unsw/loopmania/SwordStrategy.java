package unsw.loopmania;

public class SwordStrategy implements FightStrategy{
    private final int damage = 6;
    @Override
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        double totalDamage = initialDamage + (damage * 2);
        entity.setHp(currentHp - totalDamage);
    }
    
}
