package unsw.loopmania;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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
    private ComboBox gameModeComboBox;

    public void setGameSwitcher(MenuSwitcher gameSwitcher){
        this.gameSwitcher = gameSwitcher;
    }

    public String getGameMode(){
        String gameMode = gameModeComboBox.getSelectionModel().getSelectedItem().toString();
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
