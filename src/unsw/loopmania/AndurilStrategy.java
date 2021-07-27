package unsw.loopmania;

import unsw.loopmania.RareItems.AndurilSword;

public class AndurilStrategy implements FightStrategy {
    private final int damage = 12;
    @Override
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        double totalDamage = initialDamage + damage;
        //TODO: Add extra damage to bosses
        entity.setHp(currentHp - totalDamage);
    }
}
