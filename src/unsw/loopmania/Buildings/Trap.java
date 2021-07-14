package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.Enemy;
import java.util.List;


public class Trap extends Building{
    //To be decided
    private int damage = 15;
    private boolean destroyTrap;
    private Enemy toBeKilled = null;

    public Trap (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        super.setType("Trap");
        this.destroyTrap = false;
    }

    public int getDamage(){
        return this.damage;
    }

    public boolean getDestroyTrap(){
        return this.destroyTrap;
    }

    public Enemy getToBeKilled(){
        return this.toBeKilled;
    }

    public void buildingEffect(List<Enemy> enemies){
        for (Enemy e : enemies){
            if (this.getX() == e.getX() && this.getY() == e.getY()){
                e.setDamage(e.getDamage() + this.getDamage());
                if (e.getHp() <= 0){
                    this.toBeKilled = e;
                }
                //figure out a way to check if enemy should be killed
                this.destroyTrap = true;
                break;
            }
        }
    }

}