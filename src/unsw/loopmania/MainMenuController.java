package unsw.loopmania;

import java.io.File;
import java.io.IOException;

//import org.junit.jupiter.api.DisplayNameGenerator.Standard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import unsw.loopmania.LoopManiaWorld.GAME_MODE;


/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    private static final String mainmenu_song = "src/game_sounds/mainmenu.mp3";
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;
    private String gameMode;

    private MediaPlayer mediaPlayer;
    private Media sound;
    
    @FXML
    private ComboBox<String> gameModeComboBox;


    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    public String getGameMode(){
        gameMode = gameModeComboBox.getSelectionModel().getSelectedItem();
        if(gameMode == null){
            return "Standard";
        }
        return gameMode;
    }

    public MediaPlayer getPlayer(){
        sound = new Media(new File(mainmenu_song).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        return mediaPlayer;
    }

    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame() throws IOException {
        gameSwitcher.switchMenu();
    }
}
