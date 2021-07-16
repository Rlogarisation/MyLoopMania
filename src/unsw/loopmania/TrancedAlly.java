package unsw.loopmania;

public class TrancedAlly extends Ally{
    final private double baseHealth = 20;
    final private double baseAttack = 5;
    final private double initialMovingSpeed = 2;
    private int attackCounter = 0;
    public TrancedAlly(PathPosition position) {
        super(position);
        setDamage(baseAttack);
        setHp(baseHealth);
        setMovingSpeed(initialMovingSpeed);
        setFightStrategy(new BasicFightStrategy());
    }
    /**
     * If number of attacks exceed limit, tranced ally turned back into enemy
     * @return number of attacks completed by tranced ally
     */
    public int getAttackCount(){
        return attackCounter;
    }
    @Override
    public void attack(double initialDamage, MovingEntity enemy){
        super.attack(initialDamage, enemy);
        attackCounter++;
    }

}
