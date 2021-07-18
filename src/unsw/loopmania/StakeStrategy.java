package unsw.loopmania;

public class StakeStrategy implements FightStrategy{
    private final int damage = 4;
    @Override
    public void attack(double initialDamage, MovingEntity entity) {
        double currentHp = entity.getHp();
        if(entity instanceof Vampire){
            double totalDamage = initialDamage+(damage*2);
            entity.setHp(currentHp-totalDamage);
        }
        else{
            double totalDamage = initialDamage+damage;
            entity.setHp(currentHp - totalDamage);
        }
    }
    
}
