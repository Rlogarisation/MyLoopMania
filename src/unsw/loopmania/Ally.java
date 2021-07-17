package unsw.loopmania;

public class Ally extends MovingEntity{
    /**
     * Ally class baseHealth = 20 and baseAttack = 5
     */

    final private double baseHealth = 20;
    final private double baseAttack = 5;
    final private double initialMovingSpeed = 2;
    private int attackCounter = 0;
    private Enemy EnemyState = null;

    public Ally(PathPosition position) {
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


    public void setEnemyState(Enemy EnemyState) {
        this.EnemyState = EnemyState;
    }

    //based on EnemyState turn ally into an enemy
    public Enemy toEnemy(){
        this.setHp(0);
        Enemy newEnemy = null;
        if(EnemyState instanceof Zombie){
            newEnemy = new Zombie(this.getPathPosition());
        }
        if(EnemyState instanceof Vampire){
            newEnemy =  new Vampire(this.getPathPosition());
        }
        if(EnemyState instanceof Slug){
            newEnemy =  new Slug(this.getPathPosition());
        }
        return newEnemy;
    }

    
    
}
