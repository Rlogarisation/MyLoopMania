package unsw.loopmania;

public class TrancedAlly extends Ally{
    final private double baseHealth = 20;
    final private double baseAttack = 5;
    final private double initialMovingSpeed = 2;
    public TrancedAlly(PathPosition position, Enemy pastEnemyState) {
        super(position);
        setEnemyState(pastEnemyState);
        setDamage(baseAttack);
        setHp(baseHealth);
        setMovingSpeed(initialMovingSpeed);
        setFightStrategy(new BasicFightStrategy());
    }
    @Override
    public void attack(double initialDamage, MovingEntity enemy){
        super.attack(initialDamage, enemy);
        setAttackCount(getAttackCount()+1);
    }

    


}
