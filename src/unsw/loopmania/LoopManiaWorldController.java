package unsw.loopmania;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.management.openmbean.OpenDataException;

import org.codefx.libfx.listener.handle.ListenerHandle;
import org.codefx.libfx.listener.handle.ListenerHandles;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Bloom;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.loopmania.cards.*;
import unsw.loopmania.Buildings.*;
import unsw.loopmania.LoopManiaWorld.GAME_MODE;
import unsw.loopmania.RareItems.AndurilSword;
import unsw.loopmania.RareItems.ConfusingRareItem;
import unsw.loopmania.RareItems.TheOneRing;
import unsw.loopmania.RareItems.TreeStump;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;


/**
 * the draggable types.
 * If you add more draggable types, add an enum value here.
 * This is so we can see what type is being dragged.
 */
enum DRAGGABLE_TYPE{
    CARD,
    ITEM
}

/**
 * A JavaFX controller for the world.
 * 
 * All event handlers and the timeline in JavaFX run on the JavaFX application thread:
 *     https://examples.javacodegeeks.com/desktop-java/javafx/javafx-concurrency-example/
 *     Note in https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Application.html under heading "Threading", it specifies animation timelines are run in the application thread.
 * This means that the starter code does not need locks (mutexes) for resources shared between the timeline KeyFrame, and all of the  event handlers (including between different event handlers).
 * This will make the game easier for you to implement. However, if you add time-consuming processes to this, the game may lag or become choppy.
 * 
 * If you need to implement time-consuming processes, we recommend:
 *     using Task https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Task.html by itself or within a Service https://openjfx.io/javadoc/11/javafx.graphics/javafx/concurrent/Service.html
 * 
 *     Tasks ensure that any changes to public properties, change notifications for errors or cancellation, event handlers, and states occur on the JavaFX Application thread,
 *         so is a better alternative to using a basic Java Thread: https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
 *     The Service class is used for executing/reusing tasks. You can run tasks without Service, however, if you don't need to reuse it.
 *
 * If you implement time-consuming processes in a Task or thread, you may need to implement locks on resources shared with the application thread (i.e. Timeline KeyFrame and drag Event handlers).
 * You can check whether code is running on the JavaFX application thread by running the helper method printThreadingNotes in this class.
 * 
 * NOTE: http://tutorials.jenkov.com/javafx/concurrency.html and https://www.developer.com/design/multithreading-in-javafx/#:~:text=JavaFX%20has%20a%20unique%20set,in%20the%20JavaFX%20Application%20Thread.
 * 
 * If you need to delay some code but it is not long-running, consider using Platform.runLater https://openjfx.io/javadoc/11/javafx.graphics/javafx/application/Platform.html#runLater(java.lang.Runnable)
 *     This is run on the JavaFX application thread when it has enough time.
 */
public class LoopManiaWorldController {

    /**
     * squares gridpane includes path images, enemies, character, empty grass, buildings
     */
    @FXML
    private GridPane squares;

    /**
     * cards gridpane includes cards and the ground underneath the cards
     */
    @FXML
    private GridPane cards;

    /**
     * hBox contains all frontend parts except shop
     * when the shop is open, hBox is hidden
     */
    @FXML
     private StackPane stackPane;

    /**
     * anchorPaneRoot is the "background". It is useful since anchorPaneRoot stretches over the entire game world,
     * so we can detect dragging of cards/items over this and accordingly update DragIcon coordinates
     */
    @FXML
    private AnchorPane anchorPaneRoot;
    
    /**
     * hBox contains all frontend parts except shop
     * when the shop is open, hBox is hidden
     */
    @FXML
     private HBox hBox;

    /**
     * shopPane contains all frontend parts for shop
     * when the game is in progress, hBox is hidden
     */
    @FXML
     private Pane shopPane;

    /**
     * shop contains all items in shop
     * the player call buy and sell the items
     */
    @FXML
    private GridPane shop;

    /**
     * this button is used for re-opening the shop
     */
    @FXML
    private Button shopOpenButton;

    /**
     * this button is used to close the store
     */
    @FXML
    private Button exitButton;

    /**
     * it shows a message when the player click 'buy' button for an item and
     * doesn't have enough money to purchase the item
     */
    @FXML
    private BorderPane moreGold;

    /**
     * it shows a message when the player click 'sell' button for an item
     * and he doesn't have the item
     */
    @FXML
    private BorderPane donHaveItem;

    /**
     * it shows a message when the player won the game
     */
    @FXML
    private Group winMessage;

    /**
     * it shows a message when the player lost the game
     */
    @FXML
    private Group loseMessage;

    /**
     * equippedItems gridpane is for equipped items (e.g. swords, shield, axe)
     */
    @FXML
    private GridPane equippedItems;

    /**
     * unEquippedItems gridpane is for unequipped items (e.g. swords, shield, axe)
     */
    @FXML
    private GridPane unequippedInventory;

    
    @FXML
    private ProgressBar healthBar;
    static DoubleProperty healthUpdater = new SimpleDoubleProperty(.0);

    @FXML
    private Text characterGold;
    static DoubleProperty goldUpdater = new SimpleDoubleProperty(.0);
    
    @FXML
    private Text characterXP;
    static DoubleProperty XPUpdater = new SimpleDoubleProperty(.0);

    @FXML
    private GridPane allyGrid;
    
    // all image views including tiles, character, enemies, cards... even though cards in separate gridpane...
    private List<ImageView> entityImages;

    /**
     * when we drag a card/item, the picture for whatever we're dragging is set here and we actually drag this node
     */
    private DragIcon draggedEntity;

    private boolean isPaused;
    private LoopManiaWorld world;

    /**
     * runs the periodic game logic - second-by-second moving of character through maze, as well as enemies, and running of battles
     */
    private Timeline timeline;

    private Image vampireCastleCardImage;
    private Image basicEnemyImage;
    private Image vampireEnemyImage;
    private Image zombieEnemyImage;
    
    // Image for items
    private Image swordImage;
    
    private Image heroCastleImage;

    /**
     * the image currently being dragged, if there is one, otherwise null.
     * Holding the ImageView being dragged allows us to spawn it again in the drop location if appropriate.
     */
    // TODO = it would be a good idea for you to instead replace this with the building/item which should be dropped
    private ImageView currentlyDraggedImage;
    
    /**
     * null if nothing being dragged, or the type of item being dragged
     */
    private DRAGGABLE_TYPE currentlyDraggedType;

    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped over its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged over the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragOver;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dropped in the background
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> anchorPaneRootSetOnDragDropped;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged into the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragEntered;
    /**
     * mapping from draggable type enum CARD/TYPE to the event handler triggered when the draggable type is dragged outside of the boundaries of its appropriate gridpane
     */
    private EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>> gridPaneNodeSetOnDragExited;

    /**
     * object handling switching to the main menu
     */
    private MenuSwitcher mainMenuSwitcher;





    /**
     * @param world world object loaded from file
     * @param initialEntities the initial JavaFX nodes (ImageViews) which should be loaded into the GUI
     */
    public LoopManiaWorldController(LoopManiaWorld world, List<ImageView> initialEntities) {
        this.world = world;
        entityImages = new ArrayList<>(initialEntities);
        vampireCastleCardImage = new Image((new File("src/images/vampire_castle_card.png")).toURI().toString());
        basicEnemyImage = new Image((new File("src/images/slug.png")).toURI().toString());
        vampireEnemyImage = new Image((new File("src/images/vampire.png")).toURI().toString());
        zombieEnemyImage = new Image((new File("src/images/zombie.png")).toURI().toString());
        
        swordImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
        
        heroCastleImage = new Image((new File("src/images/heros_castle.png")).toURI().toString());
        currentlyDraggedImage = null;
        currentlyDraggedType = null;

        // initialize them all...
        gridPaneSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragOver = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        anchorPaneRootSetOnDragDropped = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragEntered = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
        gridPaneNodeSetOnDragExited = new EnumMap<DRAGGABLE_TYPE, EventHandler<DragEvent>>(DRAGGABLE_TYPE.class);
    }

    @FXML
    public void initialize() {
        // TODO = load more images/entities during initialization

        Image pathTilesImage = new Image((new File("src/images/32x32GrassAndDirtPath.png")).toURI().toString());
        Image inventorySlotImage = new Image((new File("src/images/empty_slot.png")).toURI().toString());
        Rectangle2D imagePart = new Rectangle2D(0, 0, 32, 32);

        // Add the ground first so it is below all other entities (inculding all the twists and turns)
        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                ImageView groundView = new ImageView(pathTilesImage);
                groundView.setViewport(imagePart);
                squares.add(groundView, x, y);
            }
        }

        healthBar.setStyle("-fx-accent: red;");
        healthBar.progressProperty().bind(healthUpdater);

        // load entities loaded from the file in the loader into the squares gridpane
        for (ImageView entity : entityImages){
            squares.getChildren().add(entity);
        }
        
        // add the ground underneath the cards
        for (int x=0; x<world.getWidth(); x++){
            ImageView groundView = new ImageView(pathTilesImage);
            groundView.setViewport(imagePart);
            cards.add(groundView, x, 0);
        }

        // add the empty slot images for the unequipped inventory
        for (int x=0; x<LoopManiaWorld.unequippedInventoryWidth; x++){
            for (int y=0; y<LoopManiaWorld.unequippedInventoryHeight; y++){
                ImageView emptySlotView = new ImageView(inventorySlotImage);
                unequippedInventory.add(emptySlotView, x, y);
            }
        }
        // create the draggable icon
        draggedEntity = new DragIcon();
        draggedEntity.setVisible(false);
        draggedEntity.setOpacity(0.7);
        anchorPaneRoot.getChildren().add(draggedEntity);
        
        setHeroShop();       

    }

    public void updateBars(){
        healthUpdater.set(((double)world.getCharacter().getHp()/100));
        characterGold.setText(String.valueOf(world.getCharacter().getGold()));
        characterXP.setText(String.valueOf(world.getCharacter().getXp()));

    }

    public void updateAllyList(){
        allyGrid.getChildren().clear();
        List<Ally> allyList = world.getAllyList();
        Iterator<Ally> allyIter = allyList.iterator(); 
        for (int x=0; x<4; x++){
            for (int y=0; y<2; y++){
                if(allyIter.hasNext()){
                    Image allyImage = new Image((new File("src/images/deep_elf_master_archer.png")).toURI().toString());
                    ImageView allyView = new ImageView(allyImage);
                    allyIter.next();
                    allyGrid.add(allyView,x, y);
                }
            }
        }
    }

    public void setGameMode(String gameMode){
        switch(gameMode){
            case "Standard":
            world.setGameMode(GAME_MODE.STANDARD);
            break;
            case "Berserker":
            world.setGameMode(GAME_MODE.BERSERKER);
            break;
            case "Survival":
            world.setGameMode(GAME_MODE.SURVIVAL);
            break;
            case "Confusing":
            world.setGameMode(GAME_MODE.CONFUSING);
        }
    }

    /**
     * create and run the timer
     */
    public void startTimer(){
        // TODO = handle more aspects of the behaviour required by the specification
        System.out.println("starting timer");
        System.out.println(world.getGameMode());
        isPaused = false;
        onLoad(world.getHeroCastle());
        // trigger adding code to process main game logic to queue. JavaFX will target framerate of 0.3 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {
            List<Enemy> defeatedEnemies = world.runBattles();
            for (Enemy e: defeatedEnemies){
                reactToEnemyDefeat(e);
            }
            if (world.getCharacterIsAlive()){
                world.runTickMoves();
                world.getCharacter().printCharacterStats();
                //Update character stats
                updateBars();
                //update ally list
                updateAllyList();
                if(world.getCharacter().hasAchievedGoal()){
                    pause();
                    winMessage.setEffect(new Bloom());
                    winMessage.setVisible(true);
                }
                printThreadingNotes("HANDLED TIMER");
            }
            //Try reviving character if it has onering
            else {
                boolean isAlive = world.reviveCharacter();
                if (!isAlive) {
                    pause();
                    loseMessage.setEffect(new Bloom());
                    loseMessage.setVisible(true);
                }
            }
            List<Enemy> newEnemies = world.possiblySpawnEnemies();
            for (Enemy newEnemy: newEnemies){
                onLoad(newEnemy);
            }
            BuildingInfo newChanges = world.buildingInteractions();
            for (Enemy newEnemy: newChanges.getNewEmeies()){
                onLoad(newEnemy);
            }
            for (Enemy defeatedEnemy: newChanges.getEnemiesKilledByTrap()){
                reactToEnemyDefeat(defeatedEnemy);
            }
            boolean openShop = world.runHeroCastle();
            if (world.getHeroCastle().getSpawnDoggie()){
                //onload doggie
            } else if (world.getHeroCastle().getSpawnElanMuske()){
                //onload elanMuske
            }
            if (openShop){
                changeToShop();
            }
            //shopOpenButton.setVisible(false);

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * pause the execution of the game loop
     * the human player can still drag and drop items during the game pause
     */
    public void pause(){
        isPaused = true;
        System.out.println("pausing");
        timeline.stop();
    }

    public void terminate(){
        pause();
    }

    /**
     * pair the entity an view so that the view copies the movements of the entity.
     * add view to list of entity images
     * @param entity backend entity to be paired with view
     * @param view frontend imageview to be paired with backend entity
     */
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entityImages.add(view);
    }

    /**
     * load a vampire card from the world, and pair it with an image in the GUI
     */
    private void loadVampireCastleCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new VampireCastleCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }
    private void loadBarracksCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new BarracksCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }
    private void loadCampFireCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new CampFireCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }
    private void loadTowerCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new TowerCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }
    private void loadTrapCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new TrapCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }
    private void loadVillageCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new VillageCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }
    private void loadZombiePitCard() {
        // TODO = load more types of card
        Card card = world.loadCard(new ZombiePitCard(new SimpleIntegerProperty(0), new SimpleIntegerProperty(0)));
        onLoad(card);
    }

    /**
     * load a sword from the world, and pair it with an image in the GUI
     */
    private void loadSword(){
        // TODO = load more types of weapon
        // start by getting first available coordinates
        Sword sword = world.addUnequippedSword();
        onLoad(sword);
    }

    /**
     * load a staff from the world, and pair it with an image in the GUI
     */
    private void loadStaff(){
        // TODO = load more types of weapon
        // start by getting first available coordinates
        Staff staff = world.addUnequippedStaff();
        onLoad(staff);
    }


    /**
     * load a stake from the world, and pair it with an image in the GUI
     */
    private void loadStake(){
        Stake stake = world.addUnequippedStake();
        onLoad(stake);
    }

    /**
     * load a stake from the world, and pair it with an image in the GUI
     */
    private void loadArmour(){
        Armour armour = world.addUnequippedArmour();
        onLoad(armour);
    }

    /**
     * load a stake from the world, and pair it with an image in the GUI
     */
    private void loadShield(){
        Shield Shield = world.addUnequippedShield();
        onLoad(Shield);
    }

    /**
     * load a stake from the world, and pair it with an image in the GUI
     */
    private void loadHelmet(){
        Helmet Helmet = world.addUnequippedHelmet();
        onLoad(Helmet);
    }

    private void loadHealthPotion(){
        HealthPotion healthPotion = world.addUnequippedHealthPotion();
        onLoad(healthPotion);
    }

    private StaticEntity loadRareItem(){
        ArrayList<String> validRareItems = world.getValidRareItems();
        Random random = new Random();
        int prob = random.nextInt(20);
        if(validRareItems.contains("the_one_ring") && prob == 1){
            StaticEntity theOneRing = world.addUnequippedTheOneRing();
            onLoadRareItem(theOneRing);
        }
        else if(validRareItems.contains("anduril_flame_of_the_west") && prob == 2){
            StaticEntity andurilSword = world.addUnequippedAndurilSword();
            onLoadRareItem(andurilSword);;
        }
        else if(validRareItems.contains("tree_stump") && prob == 3){
            StaticEntity treeStump = world.addUnequippedTreeStump();
            onLoadRareItem(treeStump);
        }
        return null;
    }


    /**
     * run GUI events after an enemy is defeated, such as spawning items/experience/gold
     * @param enemy defeated enemy for which we should react to the death of
     */
    private void reactToEnemyDefeat(Enemy enemy){
        // react to character defeating an enemy
        // in starter code, spawning extra card/weapon...

        if (enemy instanceof Doggie) {
            world.getCharacter().addDoggieCoin(1);
            world.getCharacter().flutuateDoggieCoinPrice();
        }

        if (enemy instanceof ElanMuske) {
            world.getCharacter().increaseDoggieCoinPriceDrastically();
        }

        if (enemy instanceof Slug) {
            world.getCharacter().addGold(50);
            int val = new Random().nextInt(4);
            if (val == 0){
                loadVampireCastleCard();
            } else if (val == 1){
                loadZombiePitCard();
            }
        }

        if (enemy instanceof Zombie) {
            world.getCharacter().addGold(100);
            zombieVampireDefeatCards();
            zombieVampireDefeatItem();
        }

        if (enemy instanceof Vampire){
            world.getCharacter().addGold(150);
            zombieVampireDefeatCards();
            zombieVampireDefeatItem();
        }

        loadRareItem();
    }


    /**
     * Has a 1/3 chance of giving the character a non spawning card
     */
    private void zombieVampireDefeatCards(){
        int val = new Random().nextInt(15);
        if (val == 0) loadBarracksCard();
        else if (val == 1) loadCampFireCard();
        else if (val == 2) loadTowerCard();
        else if (val == 3) loadTrapCard();
        else if (val == 4) loadVillageCard();
    }
    
    /**
     * Has a 1/3 chance of giving the character a non-rare item
     */
    private void zombieVampireDefeatItem(){
        int val = new Random().nextInt(18);
        if (val < 6 && world.getUnequippedInventoryItems().size() == 16){
            world.getCharacter().addGold(200);
        }
        else if (val == 0) loadSword();
        else if (val == 1) loadStaff();
        else if (val == 2) loadStake();
        else if (val == 3) loadShield();
        else if (val == 4) loadHelmet();
        else if (val == 5) loadArmour();
    }

    /**
     * load a card into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the cards GridPane.
     * @param card
     */
    private void onLoad(Card card) {
        ImageView view = null;
        if(card instanceof VampireCastleCard){
            Image vampireCastleCardImage = new Image((new File("src/images/vampire_castle_card.png")).toURI().toString());
            view = new ImageView(vampireCastleCardImage);
        }
        if(card instanceof BarracksCard){
            Image barracksCardImage = new Image((new File("src/images/barracks_card.png")).toURI().toString());
            view = new ImageView(barracksCardImage);
        }
        if(card instanceof CampFireCard){
            Image campFireCardImage = new Image((new File("src/images/campfire_card.png")).toURI().toString());
            view = new ImageView(campFireCardImage);
        }
        if(card instanceof TowerCard){
            Image towerCardImage = new Image((new File("src/images/tower_card.png")).toURI().toString());
            view = new ImageView(towerCardImage);
        }
        if(card instanceof TrapCard){
            Image trapCardImage = new Image((new File("src/images/trap_card.png")).toURI().toString());
            view = new ImageView(trapCardImage);
        }
        if(card instanceof VillageCard){
            Image villageCardImage = new Image((new File("src/images/village_card.png")).toURI().toString());
            view = new ImageView(villageCardImage);
        }
        if(card instanceof ZombiePitCard){
            Image zombiePitCard = new Image((new File("src/images/zombie_pit.png")).toURI().toString());
            view = new ImageView(zombiePitCard);
        }
        // FROM https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
        // note target setOnDragOver and setOnDragEntered defined in initialize method
        addDragEventHandlers(view, DRAGGABLE_TYPE.CARD, cards, squares);

        addEntity(card, view);
        cards.getChildren().add(view);
    }

    private void onLoadRareItem(StaticEntity rareItem){
        if(rareItem instanceof TheOneRing){
            onLoad((TheOneRing)rareItem);
        }
        if(rareItem instanceof AndurilSword){
            onLoad((AndurilSword)rareItem);
        }
        if(rareItem instanceof TreeStump){
            onLoad((TreeStump)rareItem);
        }
        if(rareItem instanceof ConfusingRareItem){
            ConfusingRareItem cRareItem = (ConfusingRareItem)rareItem;
            onLoadRareItem(cRareItem.getInitialRareItem());
            System.out.println(cRareItem.getInitialRareItem());
            System.out.println(cRareItem.getNewRareItem());
        }
    }

 /**
     * load potion into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param potion the specific potion being loaded
     */
    private void onLoad(HealthPotion potion){
        Image itemImage = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString());
        ImageView view = new ImageView(itemImage);
        if(potion instanceof TheOneRing){
            itemImage  = new Image((new File("src/images/the_one_ring.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(potion, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load equipment into the GUI.
     * Particularly, we must connect to the drag detection event handler,
     * and load the image into the unequippedInventory GridPane.
     * @param Equipment the specific equipment being loaded
     */
    private void onLoad(Equipment item) {
        ImageView view = null;
        Image itemImage = null;
        if(item instanceof Sword){
            itemImage = new Image((new File("src/images/basic_sword.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof Staff){
            itemImage = new Image((new File("src/images/staff.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof Stake){
            itemImage = new Image((new File("src/images/stake.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof Armour){
            itemImage = new Image((new File("src/images/armour.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof Shield){
            itemImage = new Image((new File("src/images/shield.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof Helmet){
            itemImage = new Image((new File("src/images/helmet.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof AndurilSword){
            itemImage = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString());
            view = new ImageView(itemImage);
        }
        if(item instanceof TreeStump){
            itemImage = new Image((new File("src/images/tree_stump.png")).toURI().toString());
            view = new ImageView(itemImage);

        }
        addDragEventHandlers(view, DRAGGABLE_TYPE.ITEM, unequippedInventory, equippedItems);
        addEntity(item, view);
        unequippedInventory.getChildren().add(view);
    }

    /**
     * load an enemy into the GUI
     * @param enemy
     */
    private void onLoad(Enemy enemy) {
        ImageView view = new ImageView(basicEnemyImage);
        if (enemy instanceof Vampire){
            view = new ImageView(vampireEnemyImage);
        } else if (enemy instanceof Zombie){
            view = new ImageView(zombieEnemyImage);
        }
        addEntity(enemy, view);
        squares.getChildren().add(view);
    }

    /**
     * load hero castle into the GUI
     * @param heroCastle
     */
    private void onLoad(HeroCastle heroCastle){
        ImageView view = new ImageView(heroCastleImage);
        addEntity(heroCastle, view);
        squares.getChildren().add(view);
    }

    /**
     * load a building into the GUI
     * @param building
     */
    private void onLoad(Building building){
        ImageView view = null;
        if(building instanceof VampireCastle){
            Image vampireCastleImage = new Image((new File("src/images/vampire_castle_building_purple_background.png")).toURI().toString());
            view = new ImageView(vampireCastleImage);
        }
        if(building instanceof Barracks){
            Image barracksCardImage = new Image((new File("src/images/barracks.png")).toURI().toString());
            view = new ImageView(barracksCardImage);
        }
        if(building instanceof Campfire){
            Image campFireImage = new Image((new File("src/images/campfire.png")).toURI().toString());
            view = new ImageView(campFireImage);
        }
        if(building instanceof Tower){
            Image towerImage = new Image((new File("src/images/tower.png")).toURI().toString());
            view = new ImageView(towerImage);
        }
        if(building instanceof Trap){
            Image trapImage = new Image((new File("src/images/trap.png")).toURI().toString());
            view = new ImageView(trapImage);
        }
        if(building instanceof Village){
            Image villageImage = new Image((new File("src/images/village.png")).toURI().toString());
            view = new ImageView(villageImage);
        }
        if(building instanceof ZombiePit){
            Image zombiePit = new Image((new File("src/images/zombie_pit.png")).toURI().toString());
            view = new ImageView(zombiePit);
        }
        addEntity(building, view);
        squares.getChildren().add(view);
    }

    /**
     * add drag event handlers for dropping into gridpanes, dragging over the background, dropping over the background.
     * These are not attached to invidual items such as swords/cards.
     * @param draggableType the type being dragged - card or item
     * @param sourceGridPane the gridpane being dragged from
     * @param targetGridPane the gridpane the human player should be dragging to (but we of course cannot guarantee they will do so)
     */
    private void buildNonEntityDragHandlers(DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        // TODO = be more selective about where something can be dropped
        // for example, in the specification, villages can only be dropped on path, whilst vampire castles cannot go on the path

        gridPaneSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                // TODO = for being more selective about where something can be dropped, consider applying additional if-statement logic
                /*
                 *you might want to design the application so dropping at an invalid location drops at the most recent valid location hovered over,
                 * or simply allow the card/item to return to its slot (the latter is easier, as you won't have to store the last valid drop location!)
                 */
                if (currentlyDraggedType == draggableType){
                    // problem = event is drop completed is false when should be true...
                    // https://bugs.openjdk.java.net/browse/JDK-8117019
                    // putting drop completed at start not making complete on VLAB...

                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != targetGridPane && db.hasImage()){

                        Integer cIndex = GridPane.getColumnIndex(node);
                        Integer rIndex = GridPane.getRowIndex(node);
                        int x = cIndex == null ? 0 : cIndex;
                        int y = rIndex == null ? 0 : rIndex;
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        ImageView image = new ImageView(db.getImage());

                        int nodeX = GridPane.getColumnIndex(currentlyDraggedImage);
                        int nodeY = GridPane.getRowIndex(currentlyDraggedImage);
                        switch (draggableType){
                            case CARD:
                            // TODO = spawn a building here of different types
                                Building newBuilding = convertCardToBuildingByCoordinates(nodeX, nodeY, x, y);
                                if(newBuilding == null){
                                    return;
                                }
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                onLoad(newBuilding);
                                break;
                            case ITEM:
                                Equipment equippedItem = equipItemByCoordinates(nodeX, nodeY, x, y);
                                if(equippedItem == null){
                                    return;
                                }
                                removeDraggableDragEventHandlers(draggableType, targetGridPane);
                                //removeItemByCoordinates(nodeX, nodeY);
                                targetGridPane.add(image, x, y, 1, 1);
                                break;
                            default:
                                break;
                        }
                        
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                        printThreadingNotes("DRAG DROPPED ON GRIDPANE HANDLED");
                    }
                }
                event.setDropCompleted(true);
                // consuming prevents the propagation of the event to the anchorPaneRoot (as a sub-node of anchorPaneRoot, GridPane is prioritized)
                // https://openjfx.io/javadoc/11/javafx.base/javafx/event/Event.html#consume()
                // to understand this in full detail, ask your tutor or read https://docs.oracle.com/javase/8/javafx/events-tutorial/processing.htm
                event.consume();
            }
        });

        // this doesn't fire when we drag over GridPane because in the event handler for dragging over GridPanes, we consume the event
        anchorPaneRootSetOnDragOver.put(draggableType, new EventHandler<DragEvent>(){
            // https://github.com/joelgraff/java_fx_node_link_demo/blob/master/Draggable_Node/DraggableNodeDemo/src/application/RootLayout.java#L110
            @Override
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    if(event.getGestureSource() != anchorPaneRoot && event.getDragboard().hasImage()){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                }
                if (currentlyDraggedType != null){
                    draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                }
                event.consume();
            }
        });

        // this doesn't fire when we drop over GridPane because in the event handler for dropping over GridPanes, we consume the event
        anchorPaneRootSetOnDragDropped.put(draggableType, new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (currentlyDraggedType == draggableType){
                    //Data dropped
                    //If there is an image on the dragboard, read it and use it
                    Dragboard db = event.getDragboard();
                    Node node = event.getPickResult().getIntersectedNode();
                    if(node != anchorPaneRoot && db.hasImage()){
                        //Places at 0,0 - will need to take coordinates once that is implemented
                        currentlyDraggedImage.setVisible(true);
                        draggedEntity.setVisible(false);
                        draggedEntity.setMouseTransparent(false);
                        // remove drag event handlers before setting currently dragged image to null
                        removeDraggableDragEventHandlers(draggableType, targetGridPane);
                        
                        currentlyDraggedImage = null;
                        currentlyDraggedType = null;
                    }
                }
                //let the source know whether the image was successfully transferred and used
                event.setDropCompleted(true);
                event.consume();
            }
        });
    }

    /**
     * remove the card from the world, and spawn and return a building instead where the card was dropped
     * @param cardNodeX the x coordinate of the card which was dragged, from 0 to width-1
     * @param cardNodeY the y coordinate of the card which was dragged (in starter code this is 0 as only 1 row of cards)
     * @param buildingNodeX the x coordinate of the drop location for the card, where the building will spawn, from 0 to width-1
     * @param buildingNodeY the y coordinate of the drop location for the card, where the building will spawn, from 0 to height-1
     * @return building entity returned from the world
     */
    private Building convertCardToBuildingByCoordinates(int cardNodeX, int cardNodeY, int buildingNodeX, int buildingNodeY) {
        return world.convertCardToBuildingByCoordinates(cardNodeX, cardNodeY, buildingNodeX, buildingNodeY);
    }

    /**
     * remove an item from the unequipped inventory by its x and y coordinates in the unequipped inventory gridpane
     * @param nodeX x coordinate from 0 to unequippedInventoryWidth-1
     * @param nodeY y coordinate from 0 to unequippedInventoryHeight-1
     */
    private void removeItemByCoordinates(int nodeX, int nodeY) {
        world.removeUnequippedInventoryItemByCoordinates(nodeX, nodeY);
    }

    /**
     * remove an item from the unequipped inventory by its x and y coordinates in the unequipped inventory gridpane
     * @param nodeX x coordinate from 0 to unequippedInventoryWidth-1
     * @param nodeY y coordinate from 0 to unequippedInventoryHeight-1
     */
    private Equipment equipItemByCoordinates(int nodeX, int nodeY, int x, int y) {
        //Get item type from nodeX and nodeY
        Entity item = world.getUnequippedInventoryItemEntityByCoordinates(nodeX, nodeY);
        world.getEquippedInventoryItemEntityByCoordinates(x, y);
        //Depending on type check if valid drop and return if not
        if(x == 0 && y ==1){
            if(item instanceof AttackEquipment){
            AttackEquipment newAttackEquipment = (AttackEquipment)item;
            world.equipOneItem(newAttackEquipment);
            return newAttackEquipment;
            }
            else if(item instanceof ConfusingRareItem){
                ConfusingRareItem newCr = (ConfusingRareItem)item;
                if(newCr.getSword() != null){
                    world.equipConfusingRareItem(newCr, newCr.getSword());
                    return newCr.getSword();
                }
            }
        }
        else if (item instanceof Armour && x == 1 && y == 1){
            Armour newArmour = (Armour)item;
            world.equipOneItem(newArmour);
            return newArmour;

        }
        else if (x == 2 && y == 1){
            if(item instanceof Shield){
                Shield newShield = (Shield)item;
                world.equipOneItem(newShield);
                return newShield;
            }
            else if(item instanceof ConfusingRareItem){
                ConfusingRareItem newCr = (ConfusingRareItem)item;
                if(newCr.getShield() != null){
                    world.equipConfusingRareItem(newCr, newCr.getShield());
                    return newCr.getShield();
                }
            }
        }
        else if (item instanceof Helmet && x == 1 && y == 0){
            Helmet newHelmet= (Helmet)item;
            world.equipOneItem(newHelmet);
            return newHelmet;
        }
        return null;

    }


    /**
     * add drag event handlers to an ImageView
     * @param view the view to attach drag event handlers to
     * @param draggableType the type of item being dragged - card or item
     * @param sourceGridPane the relevant gridpane from which the entity would be dragged
     * @param targetGridPane the relevant gridpane to which the entity would be dragged to
     */
    private void addDragEventHandlers(ImageView view, DRAGGABLE_TYPE draggableType, GridPane sourceGridPane, GridPane targetGridPane){
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentlyDraggedImage = view; // set image currently being dragged, so squares setOnDragEntered can detect it...
                currentlyDraggedType = draggableType;
                //Drag was detected, start drap-and-drop gesture
                //Allow any transfer node
                Dragboard db = view.startDragAndDrop(TransferMode.MOVE);
    
                //Put ImageView on dragboard
                ClipboardContent cbContent = new ClipboardContent();
                cbContent.putImage(view.getImage());
                db.setContent(cbContent);
                view.setVisible(false);

                buildNonEntityDragHandlers(draggableType, sourceGridPane, targetGridPane);

                draggedEntity.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
                switch (draggableType){
                    case CARD:
                        draggedEntity.setImage(vampireCastleCardImage);
                        break;
                    case ITEM:
                        draggedEntity.setImage(currentlyDraggedImage.getImage());
                        break;
                    default:
                        break;
                }
                
                draggedEntity.setVisible(true);
                draggedEntity.setMouseTransparent(true);
                draggedEntity.toFront();

                // IMPORTANT!!!
                // to be able to remove event handlers, need to use addEventHandler
                // https://stackoverflow.com/a/67283792
                targetGridPane.addEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
                anchorPaneRoot.addEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

                for (Node n: targetGridPane.getChildren()){
                    // events for entering and exiting are attached to squares children because that impacts opacity change
                    // these do not affect visibility of original image...
                    // https://stackoverflow.com/questions/41088095/javafx-drag-and-drop-to-gridpane
                    gridPaneNodeSetOnDragEntered.put(draggableType, new EventHandler<DragEvent>() {
                        // TODO = be more selective about whether highlighting changes - if it cannot be dropped in the location, the location shouldn't be highlighted!
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                            //The drag-and-drop gesture entered the target
                            //show the user that it is an actual gesture target
                                if(event.getGestureSource() != n && event.getDragboard().hasImage()){
                                  //  n.setOpacity(0.7);
                                }
                            }
                            event.consume();
                        }
                    });
                    gridPaneNodeSetOnDragExited.put(draggableType, new EventHandler<DragEvent>() {
                        // TODO = since being more selective about whether highlighting changes, you could program the game so if the new highlight location is invalid the highlighting doesn't change, or leave this as-is
                        public void handle(DragEvent event) {
                            if (currentlyDraggedType == draggableType){
                                n.setOpacity(1);
                            }
                
                            event.consume();
                        }
                    });
                    n.addEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
                    n.addEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
                }
                event.consume();
            }
            
        });
    }

    /**
     * remove drag event handlers so that we don't process redundant events
     * this is particularly important for slower machines such as over VLAB.
     * @param draggableType either cards, or items in unequipped inventory
     * @param targetGridPane the gridpane to remove the drag event handlers from
     */
    private void removeDraggableDragEventHandlers(DRAGGABLE_TYPE draggableType, GridPane targetGridPane){
        // remove event handlers from nodes in children squares, from anchorPaneRoot, and squares
        targetGridPane.removeEventHandler(DragEvent.DRAG_DROPPED, gridPaneSetOnDragDropped.get(draggableType));

        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_OVER, anchorPaneRootSetOnDragOver.get(draggableType));
        anchorPaneRoot.removeEventHandler(DragEvent.DRAG_DROPPED, anchorPaneRootSetOnDragDropped.get(draggableType));

        for (Node n: targetGridPane.getChildren()){
            n.removeEventHandler(DragEvent.DRAG_ENTERED, gridPaneNodeSetOnDragEntered.get(draggableType));
            n.removeEventHandler(DragEvent.DRAG_EXITED, gridPaneNodeSetOnDragExited.get(draggableType));
        }
    }

    /**
     * handle the pressing of keyboard keys.
     * Specifically, we should pause when pressing SPACE
     * @param event some keyboard key press
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        // TODO = handle additional key presses, e.g. for consuming a health potion
        switch (event.getCode()) {
        case SPACE:
            if (isPaused){
                startTimer();
            }
            else{
                pause();
            }
            break;
        default:
            break;
        }
    }

    public void setMainMenuSwitcher(MenuSwitcher mainMenuSwitcher){
        // TODO = possibly set other menu switchers
        this.mainMenuSwitcher = mainMenuSwitcher;
    }

    /**
     * this method is triggered when click button to go to main menu in FXML
     * @throws IOException
     */
    @FXML
    private void switchToMainMenu() throws IOException {
        // TODO = possibly set other menu switchers
        pause();
        mainMenuSwitcher.switchMenu();
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the world.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * 
     * note that this is put in the controller rather than the loader because we need to track positions of spawned entities such as enemy
     * or items which might need to be removed should be tracked here
     * 
     * NOTE teardown functions setup here also remove nodes from their GridPane. So it is vital this is handled in this Controller class
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        // TODO = tweak this slightly to remove items from the equipped inventory?
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());

        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        };

        // if need to remove items from the equipped inventory, add code to remove from equipped inventory gridpane in the .onDetach part
        ListenerHandle handleX = ListenerHandles.createFor(entity.x(), node)
                                               .onAttach((o, l) -> o.addListener(xListener))
                                               .onDetach((o, l) -> {
                                                    o.removeListener(xListener);
                                                    entityImages.remove(node);
                                                    squares.getChildren().remove(node);
                                                    cards.getChildren().remove(node);
                                                    equippedItems.getChildren().remove(node);
                                                    unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        ListenerHandle handleY = ListenerHandles.createFor(entity.y(), node)
                                               .onAttach((o, l) -> o.addListener(yListener))
                                               .onDetach((o, l) -> {
                                                   o.removeListener(yListener);
                                                   entityImages.remove(node);
                                                   squares.getChildren().remove(node);
                                                   cards.getChildren().remove(node);
                                                   equippedItems.getChildren().remove(node);
                                                   unequippedInventory.getChildren().remove(node);
                                                })
                                               .buildAttached();
        handleX.attach();
        handleY.attach();

        // this means that if we change boolean property in an entity tracked from here, position will stop being tracked
        // this wont work on character/path entities loaded from loader classes
        entity.shouldExist().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> obervable, Boolean oldValue, Boolean newValue) {
                handleX.detach();
                handleY.detach();
            }
        });
    }

    /**
     * we added this method to help with debugging so you could check your code is running on the application thread.
     * By running everything on the application thread, you will not need to worry about implementing locks, which is outside the scope of the course.
     * Always writing code running on the application thread will make the project easier, as long as you are not running time-consuming tasks.
     * We recommend only running code on the application thread, by using Timelines when you want to run multiple processes at once.
     * EventHandlers will run on the application thread.
     */
    private void printThreadingNotes(String currentMethodLabel){
        System.out.println("\n###########:#########");
        System.out.println("current method = "+currentMethodLabel);
        System.out.println("In application thread? = "+Platform.isFxApplicationThread());
        System.out.println("Current system time = "+java.time.LocalDateTime.now().toString().replace('T', ' '));
    }

    /**
     * 
     */
    private void changeToShop() {
        pause();
        hBox.setVisible(false);
        shopPane.setVisible(true);
        //shopOpenButton.setVisible(false);
    }

    /**
     * 
     */
    private void changeToGame() {
        hBox.setVisible(true);
        shopPane.setVisible(false);
        startTimer();
        //shopOpenButton.setVisible(true);
    }

    @FXML
    private void handleShopOpenButtonAction(ActionEvent event){
        changeToShop();         
    }

    private void setHeroShop() {

        shop.getChildren().removeAll();

        HashMap<String,StaticEntity> shopItems = world.getHeroCastle().getShopItems();

        int i = 0; int j = 0;
        for ( String key : shopItems.keySet() ) {
            Text itemName = new Text("   " + key + "      "); 
            Image itemImage = null;
            Button buyButton = new Button();
            Button sellButton = new Button();

            StaticEntity item = shopItems.get(key);
            buyButton.setOnAction(event -> {
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(0.5),
                    new KeyValue(moreGold.visibleProperty(), false)));
                Boolean isBought = world.buyOneItemBycoordinates(item.getX(),item.getY());
                if (!isBought) {
                    moreGold.setVisible(true);
                    timeline1.play();
                }

            });
            sellButton.setOnAction(event -> {
                Boolean isSold = world.sellOneItemByItem(item);
                Timeline timeline2 = new Timeline();
                    timeline2.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(donHaveItem.visibleProperty(), false)));
                if (!isSold) {
                    donHaveItem.setVisible(true);
                    timeline2.play();
                }
            });
            exitButton.setOnAction(event -> {
                changeToGame();
            });

            itemName.setStyle("-fx-font: 17 arial;"); 
            
            
            buyButton.setText("Buy");
            sellButton.setText("Sell");
            buyButton.setStyle("-fx-font: 13 arial;");
            sellButton.setStyle("-fx-font: 13 arial;");
            buyButton.setMinWidth(shop.getPrefWidth());
            sellButton.setMinWidth(shop.getPrefWidth());
            
            switch(key) {
                case "Sword": itemImage = new Image((new File("src/images/basic_sword.png")).toURI().toString()); 
                    break;
                case "Health Potion": itemImage = new Image((new File("src/images/brilliant_blue_new.png")).toURI().toString()); 
                    break;
                case "The One Ring": 
                    itemImage = new Image((new File("src/images/the_one_ring.png")).toURI().toString()); 
                    break;  
                case "Anduril": 
                    itemImage = new Image((new File("src/images/anduril_flame_of_the_west.png")).toURI().toString()); 
                    break;   
                case "Tree Stump": 
                    itemImage = new Image((new File("src/images/tree_stump.png")).toURI().toString()); 
                    break;         
                default:
                    itemImage =  new Image((new File("src/images/"+key.toLowerCase()+".png")).toURI().toString());
                    break; 
            }
            shop.add(itemName, i, j);
            shop.add(new ImageView(itemImage), i+1, j);
            if (key != "The One Ring" && key != "Anduril" && key != "Tree Stump") {
                shop.add(buyButton, i+2, j);
            }    
            shop.add(sellButton, i+3, j);
            j++;
        }
    }

}
