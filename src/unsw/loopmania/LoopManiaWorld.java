package unsw.loopmania;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.javatuples.Pair;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Buildings.Building;
import unsw.loopmania.cards.*;
import unsw.loopmania.Buildings.*;

/**
 * A backend world.
 *
 * A world can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 */
public class LoopManiaWorld {
    // TODO = add additional backend functionality

    public static final int unequippedInventoryWidth = 4;
    public static final int unequippedInventoryHeight = 4;

    /**
     * width of the world in GridPane cells
     */
    private int width;

    /**
     * height of the world in GridPane cells
     */
    private int height;

    /**
     * generic entitites - i.e. those which don't have dedicated fields
     */
    private List<Entity> nonSpecifiedEntities;

    private Character character;
    private boolean characterIsAlive;
    // TODO = add more lists for other entities, for equipped inventory items, etc...

    // TODO = expand the range of enemies
    private List<Enemy> enemyList;
    private List<Ally> allyList;

    // TODO = expand the range of cards
    private List<Card> cardEntities;

    // TODO = expand the range of items
    private List<Entity> unequippedInventoryItems;
    private List<Entity> equippedInventoryItems;

    // TODO = expand the range of buildings
    private List<Building> buildingList;
    private HeroCastle heroCastle;

    /**
     * list of x,y coordinate pairs in the order by which moving entities traverse them
     */
    private List<Pair<Integer, Integer>> orderedPath;

    // variables related to game mode 

    /**
     * checker for game mode
     */
    private GAME_MODE gameMode;

    /**
     * three different game modes 
     * 
     */
    public enum GAME_MODE{
        STANDARD,
        SURVIVAL,
        BERSERKER
    }

    /** 
     * checker for survival game mode
     */
    private Boolean gotOnePotionFromShop;

    /** 
     * checker for berserker game mode
     */
    private Boolean gotOneEquipmentFromShop;

    /**
     * create the world (constructor)
     * 
     * @param width width of world in number of cells
     * @param height height of world in number of cells
     * @param orderedPath ordered list of x, y coordinate pairs representing position of path cells in world
     */
    public LoopManiaWorld(int width, int height, List<Pair<Integer, Integer>> orderedPath) {
        this.width = width;
        this.height = height;
        nonSpecifiedEntities = new ArrayList<>();
        character = null;
        enemyList= new ArrayList<>();
        cardEntities = new ArrayList<>();
        unequippedInventoryItems = new ArrayList<>();
        this.orderedPath = orderedPath;
        buildingList = new ArrayList<>();
        this.heroCastle = new HeroCastle(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0));
        heroCastle = null;
        gameMode = GAME_MODE.STANDARD;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Character getCharacter(){
        return this.character;
    }

    public List<Enemy> getEnemyList(){
        return this.enemyList;
    }

    public List<Pair<Integer, Integer>> getOrderedPath(){
        return this.orderedPath;
    }

    public void addEnemyToEnemyList(Enemy e){
        this.enemyList.add(e);
    }

    /**
     * set the character. This is necessary because it is loaded as a special entity out of the file
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
        characterIsAlive = true;
    }

    /**
     * set the hero castle. This is necessary because it is loaded as a special entity out of the file
     * @param heroCastle
     */
    public void setHeroCastle(HeroCastle heroCastle) {
        this.heroCastle = heroCastle;
    }

    /**
     * set the gamemode. This is necessary because it is loaded as a special condition out of the file
     * @param entity
     */
    public void setGameMode(GAME_MODE gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * set the checker which checks if the character 
     * already got one health potion this time in survival mode. 
     * @param gotOnePotionFromShop
     */
    public void setgotOnePotionFromShop(Boolean gotOnePotionFromShop) {
        this.gotOnePotionFromShop = gotOnePotionFromShop;
    }

    /**
     * set the checker which checks if the character 
     * already got one equipment this time in berserker mode. 
     * @param gotOneEquipmentFromShop

     */
    public void setgotOneEquipmentFromShop(Boolean gotOneEquipmentFromShop) {
        this.gotOneEquipmentFromShop = gotOneEquipmentFromShop;
    }


    /**
     * add a generic entity (without it's own dedicated method for adding to the world)
     * @param entity
     */
    public void addEntity(Entity entity) {
        // for adding non-specific entities (ones without another dedicated list)
        // TODO = if more specialised types being added from main menu, add more methods like this with specific input types...
        nonSpecifiedEntities.add(entity);
    }

    /**
     * spawns enemies if the conditions warrant it, adds to world
     * @return list of the enemies to be displayed on screen
     */
    public List<Enemy> possiblySpawnEnemies(){
        // TODO = expand this very basic version
        Pair<Integer, Integer> pos = possiblyGetBasicEnemySpawnPosition();
        List<Enemy> spawningEnemies = new ArrayList<>();
        if (pos != null){
            int indexInPath = orderedPath.indexOf(pos);
            Enemy slug = new Slug(new PathPosition(indexInPath, orderedPath));
            enemyList.add(slug);
            spawningEnemies.add(slug);
        }
        return spawningEnemies;
    }

    /**
     * kill an enemy
     * @param enemy enemy to be killed
     */
    public void killEnemy(Enemy enemy){
        enemy.destroy();
        enemyList.remove(enemy);
    }

    /**
     * remove a building
     * @param building building to be removed
     */
    public void removeBuilding(Building building){
        building.destroy();
        buildingList.remove(building);
    }

    /**
     * The function addEnemy is used for adding transformed enemy by zombie's effect
     * @param enemy the enemy will be added to the enemyList.
     */
    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }

    /**
     * Add ally into ally list 
     * @param position where it has been spawn.
     */
    public void addAlly(PathPosition position) {
        Ally newAlly = new Ally(position);
        allyList.add(newAlly);
    }

    /**
     * Remove an ally from the ally list.
     * @param selectedAlly ally need to be removed.
     */
    public void removeAlly(Ally selectedAlly) {
        allyList.remove(selectedAlly);
    }


    /**
     * Run the expected battles in the world, based on current world state.
     * runBattle function will end until either surrounding enemies all dead, or character is dead.
     * a battle will commence when the Character moves within the battle radius of an enemy on the path.
     * Those enemies for which the Character is within their support radius will join the battle.
     * @return list of enemies which have been killed
     */
    public List<Enemy> runBattles() {
        List<Enemy> defeatedEnemies = new ArrayList<Enemy>();
        List<Enemy> enemiesJoiningBattle = determineEnemyEngagement();
        int allyIndex = 0;
        int enemyIndex = 0;


        // Battle between ally and enemy.
        while (allyIndex < allyList.size()) {
            Ally currentAlly = allyList.get(allyIndex);
            Enemy currentEnemy = enemiesJoiningBattle.get(enemyIndex);

            currentAlly.attack(currentAlly.getDamage(), currentEnemy);
            currentEnemy.attack(currentEnemy.getDamage(), currentAlly);
            // Transform the currentAlly into another Zombie.
            if (currentEnemy instanceof Zombie && chanceGenerator(0.3)) {
                removeAlly(currentAlly);
                currentAlly.setHp(0);
                Zombie newZombie = new Zombie(currentAlly.getPathPosition());
                enemiesJoiningBattle.add(newZombie);
                enemyList.add(newZombie);
            }

            if (currentAlly.getHp() <= 0) {
                allyIndex++;
            }
            if (currentEnemy.getHp() <= 0) {
                enemyIndex++;
                defeatedEnemies.add(currentEnemy);
            }
        }

        // Battle with character and enemy when all allies are dead.
        while (enemyIndex < enemiesJoiningBattle.size()) {
            Enemy currentEnemy = enemiesJoiningBattle.get(enemyIndex);

            character.attack(character.getDamage(), currentEnemy);
            currentEnemy.attack(currentEnemy.getDamage(), character);

            if (character.getHp() <= 0) {
                characterIsAlive = false;
                break;
            }
            if (currentEnemy.getHp() <= 0) {
                enemyIndex++;
                defeatedEnemies.add(currentEnemy);
            }

        }

        for (Enemy e: defeatedEnemies){
            // IMPORTANT = we kill enemies here, because killEnemy removes the enemy from the enemies list
            // if we killEnemy in prior loop, we get java.util.ConcurrentModificationException
            // due to mutating list we're iterating over
            killEnemy(e);
        }
        return defeatedEnemies;
    }


    /**
     * This function determines the amount of enemy will engaged into the battle.
     * This function search for enemy who is within battle range with character,
     * if so, search all enemy who is within the support radius of current enemy,
     * add all the enemies who will engaged into the battle into array. 
     * @return array of enemies who will battle within this fight.
     */
    public List<Enemy> determineEnemyEngagement() {
        List<Enemy> enemyJoiningBattle = new ArrayList<Enemy>();
        
        // Search for enemy who is within battle range with character.
        for (Enemy e: enemyList){
            if (withinRange(e, character, e.getBattleRadius())){
                // Searching for backup enemy who can support current enemy, 
                // in which backup enemy must in support radius.
                for (Enemy backupEnemy: enemyList) {
                    if (backupEnemy.getHp() > 0 && 
                    withinRange(backupEnemy, e, backupEnemy.getSupportRadius()) &&
                    !enemyJoiningBattle.contains(backupEnemy)) {
                        enemyJoiningBattle.add(backupEnemy);
                    }
                }
            }
        }
        return enemyJoiningBattle;
    }

    /**
     * The chance generator function takes in a value between 0 to 1.0 as double,
     * which is the chance of selecting, 
     * e.g: there is 30% of selecting if you enter 0.3.
     * @param chance between 0 to 1 as percentage.
     * @return ture if seleted else return false as boolean.
     */
    public boolean chanceGenerator(double chance) {
        double chanceOfCriticalBite = (new Random()).nextDouble();
        if (chanceOfCriticalBite <= chance) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Function to determine the whether a and b are within distance.
     * @param a First Entity.
     * @param b Second Entity.
     * @param distance The distance between these two entity.
     * @return
     */
    public boolean withinRange(Entity a, Entity b, double distance) {
        return Math.pow((a.getX()-b.getX()), 2) +  Math.pow((a.getY()-b.getY()), 2) < Math.pow(distance, 2);
    }

    /**
     * Iterate through the list of buildings and run the method building effect
     * Apply relevant changes to the newChanges class
     * The controller can interpret the data in newChanges
     * @return newChanges - Lists of newEnemies, enemiesKille and trapsDestroyed
     */
    public BuildingInfo buildingInteractions(){

        BuildingInfo newChanges = new BuildingInfo();
        character.setTowerDamage(0);

        for (Building b : buildingList){
            b.buildingEffect(this, newChanges);
        }

        return newChanges;
    }

    /**
     * Run the building effect of Hero's Castle
     * It will notify zombiePit and vampireCastle to update numCycle
     * @return true if shop should be opened, false otherwise
     */
    public boolean runHeroCastle(){
        return heroCastle.buildingEffect(this);
    }

    /**
     * spawn a card in the world and return the card entity
     * @return a card to be spawned in the controller as a JavaFX node
     */
    public Card loadCard(Card newCard){
        // if adding more cards than have, remove the first card...
        if (cardEntities.size() >= getWidth()){
            // TODO = give some cash/experience/item rewards for the discarding of the oldest card
            removeCard(0);
        }
        if(newCard instanceof VampireCastleCard){
            newCard = new VampireCastleCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        if(newCard instanceof BarracksCard){
            newCard = new BarracksCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        if(newCard instanceof CampFireCard){
            newCard = new CampFireCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        if(newCard instanceof TowerCard){
            newCard = new TowerCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        if(newCard instanceof TrapCard){
            newCard = new TrapCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        if(newCard instanceof ZombiePitCard){
            newCard = new ZombiePitCard(new SimpleIntegerProperty(cardEntities.size()), new SimpleIntegerProperty(0));
        }
        cardEntities.add(newCard);
        return newCard;
    }

    /**
     * remove card at a particular index of cards (position in gridpane of unplayed cards)
     * @param index the index of the card, from 0 to length-1
     */
    private void removeCard(int index){
        Card c = cardEntities.get(index);
        int x = c.getX();
        c.destroy();
        cardEntities.remove(index);
        shiftCardsDownFromXCoordinate(x);
    }

    /**
     * spawn a sword in the world and return the sword entity
     * @return a sword to be spawned in the controller as a JavaFX node
     */
    public Sword addUnequippedSword(){
        // TODO = expand this - we would like to be able to add multiple types of items, apart from swords
        Pair<Integer, Integer> firstAvailableSlot = getFirstAvailableSlotForItem();
        if (firstAvailableSlot == null){
            // eject the oldest unequipped item and replace it... oldest item is that at beginning of items
            // TODO = give some cash/experience rewards for the discarding of the oldest sword
            removeItemByPositionInUnequippedInventoryItems(0);
            firstAvailableSlot = getFirstAvailableSlotForItem();
        }
        
        // now we insert the new sword, as we know we have at least made a slot available...
        Sword sword = new Sword(new SimpleIntegerProperty(firstAvailableSlot.getValue0()), new SimpleIntegerProperty(firstAvailableSlot.getValue1()));
        unequippedInventoryItems.add(sword);
        return sword;
    }

    /**
     * remove an item by x,y coordinates
     * @param x x coordinate from 0 to width-1
     * @param y y coordinate from 0 to height-1
     */
    public void removeUnequippedInventoryItemByCoordinates(int x, int y){
        Entity item = getUnequippedInventoryItemEntityByCoordinates(x, y);
        removeUnequippedInventoryItem(item);
    }

    /**
     * run moves which occur with every tick without needing to spawn anything immediately
     */
    public void runTickMoves(){
        character.moveDownPath();
        moveAllEnemies();
    }

    /**
     * remove an item from the unequipped inventory
     * @param item item to be removed
     */
    private void removeUnequippedInventoryItem(Entity item){
        item.destroy();
        unequippedInventoryItems.remove(item);
    }

    /**
     * return an unequipped inventory item by x and y coordinates
     * assumes that no 2 unequipped inventory items share x and y coordinates
     * @param x x index from 0 to width-1
     * @param y y index from 0 to height-1
     * @return unequipped inventory item at the input position
     */
    private Entity getUnequippedInventoryItemEntityByCoordinates(int x, int y){
        for (Entity e: unequippedInventoryItems){
            if ((e.getX() == x) && (e.getY() == y)){
                return e;
            }
        }
        return null;
    }

    /**
     * remove item at a particular index in the unequipped inventory items list (this is ordered based on age in the starter code)
     * @param index index from 0 to length-1
     */
    private void removeItemByPositionInUnequippedInventoryItems(int index){
        Entity item = unequippedInventoryItems.get(index);
        item.destroy();
        unequippedInventoryItems.remove(index);
    }

    /**
     * get the first pair of x,y coordinates which don't have any items in it in the unequipped inventory
     * @return x,y coordinate pair
     */
    private Pair<Integer, Integer> getFirstAvailableSlotForItem(){
        // first available slot for an item...
        // IMPORTANT - have to check by y then x, since trying to find first available slot defined by looking row by row
        for (int y=0; y<unequippedInventoryHeight; y++){
            for (int x=0; x<unequippedInventoryWidth; x++){
                if (getUnequippedInventoryItemEntityByCoordinates(x, y) == null){
                    return new Pair<Integer, Integer>(x, y);
                }
            }
        }
        return null;
    }

    /**
     * shift card coordinates down starting from x coordinate
     * @param x x coordinate which can range from 0 to width-1
     */
    private void shiftCardsDownFromXCoordinate(int x){
        for (Card c: cardEntities){
            if (c.getX() >= x){
                c.x().set(c.getX()-1);
            }
        }
    }

    /**
     * move all enemies
     */
    private void moveAllEnemies() {
        // TODO = expand to more types of enemy
        for (Enemy e: enemyList){
            e.move();
            //Reset the campfireInRange for vampire
            if (e instanceof Vampire){
                ((Vampire)e).setCampfireInRange(false);
            }
        }
    }

    /**
     * get a randomly generated position which could be used to spawn an enemy
     * @return null if random choice is that wont be spawning an enemy or it isn't possible, or random coordinate pair if should go ahead
     */
    private Pair<Integer, Integer> possiblyGetBasicEnemySpawnPosition(){
        // TODO = modify this
        
        // has a chance spawning a basic enemy on a tile the character isn't on or immediately before or after (currently space required = 2)...
        Random rand = new Random();
        int choice = rand.nextInt(2); // TODO = change based on spec... currently low value for dev purposes...
        // TODO = change based on spec
        if ((choice == 0) && (enemyList.size() < 2)){
            List<Pair<Integer, Integer>> orderedPathSpawnCandidates = new ArrayList<>();
            int indexPosition = orderedPath.indexOf(new Pair<Integer, Integer>(character.getX(), character.getY()));
            // inclusive start and exclusive end of range of positions not allowed
            int startNotAllowed = (indexPosition - 2 + orderedPath.size())%orderedPath.size();
            int endNotAllowed = (indexPosition + 3)%orderedPath.size();
            // note terminating condition has to be != rather than < since wrap around...
            for (int i=endNotAllowed; i!=startNotAllowed; i=(i+1)%orderedPath.size()){
                orderedPathSpawnCandidates.add(orderedPath.get(i));
            }

            // choose random choice
            Pair<Integer, Integer> spawnPosition = orderedPathSpawnCandidates.get(rand.nextInt(orderedPathSpawnCandidates.size()));

            return spawnPosition;
        }
        return null;
    }

    /**
     * remove a card by its x, y coordinates
     * @param cardNodeX x index from 0 to width-1 of card to be removed
     * @param cardNodeY y index from 0 to height-1 of card to be removed
     * @param buildingNodeX x index from 0 to width-1 of building to be added
     * @param buildingNodeY y index from 0 to height-1 of building to be added
     */
    public Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        Card card = null;
        for (Card c: cardEntities){
            if(c.getX() == cardNodeX && c.getY()==cardNodeY){
                card = c;
            }
        }

        //check if card can be placed into newLocation
        Pair<Integer, Integer> newLocation = new Pair<>(buildingNodeX, buildingNodeY);
        if(card.validDrop(orderedPath, newLocation) == false){
            System.out.println("invalid drop");
            return null;

        }

        // now spawn building
        Building newBuilding = card.toBuilding(new SimpleIntegerProperty(buildingNodeX), new SimpleIntegerProperty(buildingNodeY));
        buildingList.add(newBuilding);
        System.out.println(buildingList);

        //attach zombiePit and vampireCastle as observers to Hero's Castle
        if (newBuilding instanceof VampireCastle){
            this.heroCastle.attach((VampireCastle)newBuilding);
        }
        else if (newBuilding instanceof ZombiePit){
            this.heroCastle.attach((ZombiePit)newBuilding);
        }

        // destroy the card
        card.destroy();
        cardEntities.remove(card);
        shiftCardsDownFromXCoordinate(cardNodeX);

        return newBuilding;
    }

    /**
     * it sells one item from unequipped inventory items 
     * and the character receives gold as a reward.
     * @param itemNodeX indicates the x-coordinate of the chosen item
     * @param itemNodeY indicates the y-coordinate of the chosen item
     */
    public void sellOneItemBycoordinates(int itemNodeX, int itemNodeY) {
        Entity chosenItem = null;
        int itemPrice = 0;
        for (Entity item: unequippedInventoryItems) {
            if (itemNodeX == item.getX() && itemNodeY == item.getY()) {
                chosenItem = item;
                if (chosenItem instanceof Equipment) {
                    itemPrice = ((Equipment) item).getPrice();
                    break;
                }
                if (chosenItem instanceof HealthPotion) {
                    itemPrice = ((HealthPotion) item).getPrice();
                    break;
                }    
                if (chosenItem instanceof RareItem) {
                    itemPrice = ((RareItem) item).getPrice();
                    break;
                }
            }
        }
        if (chosenItem != null) {
            chosenItem.destroy();
            removeUnequippedInventoryItem(chosenItem);
            this.character.addGold((double) (itemPrice / 2));
        }    
        
    }

    /**
     * character buys one item from the shop in hero castle 
     * and the character pay gold as a currency for the item.
     * @param itemNodeX indicates the x-coordinate of the chosen item in the shop
     * @param itemNodeY indicates the y-coordinate of the chosen item in the shop
     */
    public void buyOneItemBycoordinates(int itemNodeX, int itemNodeY) {
        
        HashMap<String,StaticEntity> itemShop = this.heroCastle.getShopItems();
        StaticEntity chosenItem = null;
        Double characterGold = this.character.getGold();
        int itemPrice = 0;
        
        
        for (StaticEntity item : itemShop.values()) {
            if (itemNodeX == item.getX() && itemNodeY == item.getY()) {
                chosenItem = item;
                
                // if the game mode is berserker, set the equipement checker
                // which checks if the character already got one equipment on this turn 
                if (gameMode == GAME_MODE.BERSERKER && this.gotOneEquipmentFromShop == null) {
                    this.setgotOneEquipmentFromShop(false);
                }

                // if the game mode is berserker, set the potion checker
                // which checks if the character already got one potion on this turn
                if (gameMode == GAME_MODE.SURVIVAL && this.gotOnePotionFromShop == null) {
                    this.setgotOnePotionFromShop(false);
                }

                // case1) get an equipment
                if (chosenItem instanceof Equipment) {
                    itemPrice = ((Equipment) chosenItem).getPrice();
                    if (gameMode == GAME_MODE.BERSERKER && !gotOneEquipmentFromShop 
                        && characterGold >= itemPrice) {
                        this.addUnequippedSword();
                        // if the game mode is berserker, the equipment price is 50% more expensive
                        this.character.setGold(characterGold - (1.5)*itemPrice);
                        this.setgotOneEquipmentFromShop(true);
                        break;
                    }

                    if (gameMode != GAME_MODE.BERSERKER && characterGold >= itemPrice) {
                        this.addUnequippedSword();
                        this.character.setGold(characterGold - itemPrice);
                        break;
                    }   
                }

                // case1) get a health potion
                if (chosenItem instanceof HealthPotion) {
                    itemPrice = ((HealthPotion) chosenItem).getPrice();
                    if (gameMode == GAME_MODE.SURVIVAL && !gotOnePotionFromShop 
                        && characterGold >= itemPrice) {
                        this.addUnequippedSword();
                        // if the game mode is survival, the equipment is twice as expensive
                        this.character.setGold(characterGold - 2*itemPrice);
                        this.setgotOnePotionFromShop(true);
                        break;
                    } 
                    if (gameMode != GAME_MODE.SURVIVAL &&  characterGold >= itemPrice) {
                        this.addUnequippedSword();
                        this.character.setGold(characterGold - itemPrice);
                        break;
                    }   
            }
        }
        
        }
    }
}
