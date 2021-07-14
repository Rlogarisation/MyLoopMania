package unsw.loopmania;

public interface FightStrategy {
    //Add if statment, if character has helmet reduce attacks down 
    public void attack(double initialDamage, Enemy enemy);
}
