package unsw.loopmania;

import unsw.loopmania.RareItems.AndurilSword;

public class AndurilStrategy implements FightStrategy {
    private final int damage = 8;
    @Override
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        double totalDamage = initialDamage + damage;
        if(entity instanceof ElanMuske){
            totalDamage= totalDamage*3;
        }
        entity.setHp(currentHp - totalDamage);
    }
}
