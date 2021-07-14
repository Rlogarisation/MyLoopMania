package unsw.loopmania.cards;
import java.io.File;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.Buildings.*;

public class CampFireCard extends Card {
    public CampFireCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setImage(new Image((new File("src/images/campfire_card.png")).toURI().toString()));
    }

    @Override
    public Campfire toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Campfire campfire = new Campfire(x,y);
        return campfire;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }    

    
}
