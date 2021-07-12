package unsw.loopmania.cards;

import java.io.File;
import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.VampireCastleBuilding;

public class VillageCard extends Card {
    public VillageCard(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        setImage(new Image((new File("src/images/village_card.png")).toURI().toString()));
    }    

    public VampireCastleBuilding toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        VampireCastleBuilding newBuilding = new VampireCastleBuilding(x, y);
        return newBuilding;
    }

    @Override
    public boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer, Integer> dropLocation) {
        // TODO Auto-generated method stub
        return false;
    }
}
