package unsw.loopmania.cards;
import java.io.File;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.Buildings.*;

public class BarracksCard extends Card {
    public BarracksCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setImage(new Image((new File("src/images/barracks_card.png")).toURI().toString()));
    }

    @Override
    public Barracks toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        Barracks barracks = new Barracks(x, y);
        return barracks;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }  

    
}
