package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.*;
import unsw.loopmania.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.javatuples.Pair;


public class HeroCastle extends StaticEntity{ 

    private static final int nItems = 7;

    private List<VampireCastle> vampireCastles;
    private List<ZombiePit> zombiePits;
    private int numCyclesComplete;
    private int numCyclesGoal;
    private HashMap<String,StaticEntity> shop;
    private boolean spawnDoggie;
    private boolean spawnElanMuske;

    public HeroCastle (SimpleIntegerProperty x, SimpleIntegerProperty y){
        super(x, y);
        this.vampireCastles = new ArrayList<VampireCastle>();
        this.zombiePits = new ArrayList<ZombiePit>();
        this.numCyclesComplete = 0;
        this.numCyclesGoal = 1;
        initializeShop();
        this.spawnDoggie = false;
        this.spawnElanMuske = false;
    }

    /**
     * Attach new VampireCastles to the observer list vampireCastles
     * @param building is added to vampireCastles list
     */
    public void attach(VampireCastle building){
        this.vampireCastles.add(building);
    }

    /**
     * Attach new ZombiePit to the observer list zombiePits
     * @param building is added to zombiePits list
     */
    public void attach(ZombiePit building){
        this.zombiePits.add(building);
    }

    public boolean getSpawnDoggie(){
        return this.spawnDoggie;
    }

    public boolean getSpawnElanMuske(){
        return this.spawnElanMuske;
    }

    public void setSpawnDoggie(boolean yesNo){
        this.spawnDoggie = yesNo;
    }

    public void setSpawnElanMuske(boolean yesNo){
        this.spawnElanMuske = yesNo;
    }

    /**
     * Notify the observers in the list vampireCastles and zombiePits
     * Call the update method in them
     */
    public void notifyAllObservers(){
        for (VampireCastle building : vampireCastles){
            building.update();
        }
        for (ZombiePit building : zombiePits){
            building.update();
        }
    }

    /**
     * Apply the building effect of Hero's Castle
     * If the character has the same position 
     * -> Notify all the observer lists
     * -> Check if it is the right cycle to open the shop
     * @param lmw contains the character position
     * @return true if the shop is to be opened
     */
    public boolean buildingEffect(LoopManiaWorld lmw){
        Character character = lmw.getCharacter();
        
        if (this.getX() == character.getX() && this.getY() == character.getY()){
            character.setCycleCount(character.getCycleCount()+1);
            notifyAllObservers();
            this.numCyclesComplete = this.numCyclesComplete + 1;

            if (character.getCycleCount() == 20) spawnBoss("Doggie", lmw);
            else if (character.getCycleCount() == 40 && character.getXp() >= 10000) spawnBoss("ElanMuske", lmw);

            if (this.numCyclesComplete == this.numCyclesGoal){
                this.numCyclesComplete = 0;
                this.numCyclesGoal = this.numCyclesGoal + 1; 
                return true;
            }
        }

        return false;
    }

    /**
     * initialise the shop in the castle
     */
    public void initializeShop() {
        HashMap<String,StaticEntity> shop = new HashMap<String,StaticEntity>(nItems);
        SimpleIntegerProperty x = new SimpleIntegerProperty(0);
        int y = 0;
        shop.put("Sword", new Sword(x, new SimpleIntegerProperty(y)));
        shop.put("Stake", new Stake(x, new SimpleIntegerProperty(y+1)));
        shop.put("Staff", new Staff(x, new SimpleIntegerProperty(y+2)));
        shop.put("Armour", new Armour(x, new SimpleIntegerProperty(y+3)));
        shop.put("Shield", new Shield(x, new SimpleIntegerProperty(y+4)));
        shop.put("Helmet", new Helmet(x, new SimpleIntegerProperty(y+5)));
        shop.put("Health Potion", new HealthPotion(x, new SimpleIntegerProperty(y+6)));
        shop.put("DoggieCoin", new DoggieCoin(x, new SimpleIntegerProperty(y+6)));
        this.shop = shop;
    }

    /**
     * getter for shop in the castle
     * @return shop in the castle
     */
    public HashMap<String,StaticEntity> getShopItems() {
        return this.shop;
    }

    
    public void spawnBoss(String s, LoopManiaWorld lmw){
        Pair<Integer, Integer> pos = lmw.possiblyGetBossSpawnPosition();
        if (pos != null){
            int indexInPath = lmw.getOrderedPath().indexOf(pos);
            switch(s){
                case "ElanMuske":
                    Enemy elanMuske = new ElanMuske(new PathPosition(indexInPath, lmw.getOrderedPath()));
                    lmw.addEnemy(elanMuske);
                    this.spawnElanMuske = true;
                    break;
                case "Doggie":
                    Enemy doggie = new Doggie(new PathPosition(indexInPath, lmw.getOrderedPath()));
                    lmw.addEnemy(doggie);
                    this.spawnDoggie = true;
                    break;
                default:
                    break;
            }
        }
    }

}
