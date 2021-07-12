package unsw.loopmania.cards;
import java.io.File;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.VampireCastleBuilding;

public class CampFireCard extends Card {
    public CampFireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setImage(new Image((new File("src/images/campfire_card.png")).toURI().toString()));
    }

    @Override
    public VampireCastleBuilding toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }    

    
}
