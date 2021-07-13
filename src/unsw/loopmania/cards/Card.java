package unsw.loopmania.cards;

import java.util.List;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import unsw.loopmania.StaticEntity;
import unsw.loopmania.*;

/**
 * a Card in the world
 * which doesn't move
 */


public abstract class Card extends StaticEntity{
    Image cardImage;
    public Card(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void setImage(Image newImage){
        this.cardImage = newImage;
    }

    //Returns corresponding building object 
    public abstract VampireCastleBuilding toBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y);


    //check if card is in a valid drop
    public abstract boolean validDrop(List<Pair<Integer, Integer>> orderedPath, Pair<Integer,Integer> dropLocation);
}
