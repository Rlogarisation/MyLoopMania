package unsw.loopmania.cards;

import java.io.File;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.Buildings.*;

public class ZombiePitCard extends Card{
    public ZombiePitCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setImage(new Image((new File("src/images/zombie_pit_card.png")).toURI().toString()));
    }    

    public ZombiePit toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        ZombiePit newBuilding = new ZombiePit(x, y);
        return newBuilding;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }
}
