package test;
import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.cards.*;

public class CardTest {
    
    public void toBuildingTest(){
    //Create a new vampireCastleCard
    SimpleIntegerProperty x = new SimpleIntegerProperty(0);
    SimpleIntegerProperty y = new SimpleIntegerProperty(0);
    VampireCastleCard newCard = new VampireCastleCard(x,y);
    VampireCastleBuilding newBuilding = newCard.toBuilding(x, y);
    assert(newBuilding.x() == x && newBuilding.y() == y);
    
    //Use toBuilding and see if instance returned is indeed a building
    }

    public void ValidDropTest(){
        
    }
}
