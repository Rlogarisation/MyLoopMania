package unsw.loopmania;

public class StaffStrategy implements FightStrategy{
    private final int damage = 3;
    public void attack(double initialDamage, Enemy enemy) {
        // 7% chance of trance occuring with Staff Attack
        if(Math.random() <= 0.07){
            //If enemy is tranced, handle adding and deleting trancedAlly in loopmaniaWorld
            enemy.setIsTranced(true);
        }
        double currentHp = enemy.getHp();
        double totalDamage = initialDamage+damage;
        enemy.setHp(currentHp - totalDamage);
        }
    }
    
