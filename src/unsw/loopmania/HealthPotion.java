package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * A health potion
 * @author Kihwan Baek
 *
 */
public class HealthPotion extends StaticEntity {

    private final int price = 200;

    public HealthPotion(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x,y);
        /* if (gameMode = survival) {
            this.setPrice(price*2);
        } else {
            this.setPrice(price);
        }
        */
    }    
    
    public int getPrice() {
        return this.price;
    }

    /*
    // I'll move this function to Character
    public void refillHealth() {
        // find a potion available from the unequippedInventory
        Boolean isPotion = false;
        for (Entity item:unequippedInventoryItems) {
            if (item instanceof HealthPotion) {
                unequippedInventoryItems.remove(item);
                isPotion = true;
            }
        }
    
        if (isPotion) {
            int currentHp = this.character.getHp();
            if (currentHp >= 70) {
                character.setHp(100);
            } else {
                character.setHp(currentHp+30);
            }
        }
    }
    */

}