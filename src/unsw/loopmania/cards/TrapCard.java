package unsw.loopmania.cards;

import java.io.File;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.Buildings.*;

public class TrapCard extends Card{
    public TrapCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setImage(new Image((new File("src/images/trap_card.png")).toURI().toString()));
    }   
    
    public Trap toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Trap newBuilding = new Trap(x, y);
        return newBuilding;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }
}
