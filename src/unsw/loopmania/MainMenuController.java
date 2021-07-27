package unsw.loopmania;

import java.io.IOException;

import org.junit.jupiter.api.DisplayNameGenerator.Standard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import unsw.loopmania.LoopManiaWorld.GAME_MODE;


/**
 * controller for the main menu.
 * TODO = you could extend this, for example with a settings menu, or a menu to load particular maps.
 */
public class MainMenuController {
    /**
     * facilitates switching to main game
     */
    private MenuSwitcher gameSwitcher;
    private String gameMode;
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


    /**
     * facilitates switching to main game upon button click
     * @throws IOException
     */
    @FXML
    private void switchToGame() throws IOException {
        gameSwitcher.switchMenu();
    }
}
