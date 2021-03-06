package warlords.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import warlords.WarlordsController;
import warlords.model.Game;

public class CampaignMenuViewController {
	private WarlordsController warlordsController;
	private int isSelected;
	private ArrayList<Text> menuList = new ArrayList<Text>();
	Scene scene;
	private EventHandler<KeyEvent> handler;
	private AudioClip menuScroller = new AudioClip(new File("sounds/menuScroller.wav").toURI().toString());
	
	public CampaignMenuViewController() {
		
	}
	
	// Links the controller to the WarlordsController and scene
	public void setWarlordsController(WarlordsController warlordsController, Scene scene) {
		this.warlordsController = warlordsController;
		this.scene = scene;
		// Add menu items to controller
		menuList.add(cont);
		menuList.add(newCampaign);
		menuList.add(levelSelect);
		menuList.add(back);
		
		prompt.setVisible(false);
		isSelected = 0;	
		
		// Set custom fonts 
		Font myFont = null;
		Font titleFont = null;
        try {
            myFont = Font.loadFont(new FileInputStream(new File("Fonts/evanescent_p.ttf")), 84);
            titleFont = Font.loadFont(new FileInputStream(new File("Fonts/evanescent_p.ttf")), 96);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i=0; i<menuList.size(); i++) {
        	menuList.get(i).setFont(myFont);
        }
        title.setFont(titleFont);
        updateSelection();
        
        // Create a key handler for the scene
        handler = new EventHandler<KeyEvent>() {
			@Override
		    public void handle(KeyEvent t) {
				menuScroller.play();
				switch(t.getCode()) {
					case UP:
						upArrowPressed();
						updateSelection();
						break;
					case DOWN:
						downArrowPressed();
						updateSelection();
						break;
					case ENTER:
						enterPressed();
					default:
						break;					
				}
			}
        };    
    	scene.addEventHandler(KeyEvent.KEY_PRESSED, handler);
	}
	
	// Refresh the selection menu
	public void updateSelection() {
		for (int i=0; i<menuList.size(); i++) {
			if (i == isSelected) {
				menuList.get(i).setFill(Color.valueOf("#FFFFFF")); // Selected item is white
			} else {
				menuList.get(i).setFill(Color.valueOf("#686868")); // Unselected is grey
			}			
		}	
	}
	
	// If the user presses the up arrow
	public void upArrowPressed() {
		if (isSelected == 0) {
			isSelected = menuList.size()-1;
		} else {
			isSelected--;
		}
	}
	
	// If the user presses the down arrow
	public void downArrowPressed() {
		if (isSelected == menuList.size()-1) {
			isSelected = 0;
		} else {
			isSelected++;
		}
	}
	
	// Removes the key handler from the scene, moves to the next view
	public void enterPressed() {
		if (isSelected == 0) {
			MainMenuViewController.isCampaign = true;
			switch(MainMenuViewController.level){
			case 0:
				prompt.setVisible(true);
				break;
			case 1:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 0;
				Game.reverseControls = false;
				GameViewController.gameSpeed = 15;
				warlordsController.showGameView(); //Creates level 1 with settings specified in this case
				break;
			case 2:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 1;
				Game.reverseControls = false;
				GameViewController.gameSpeed = 15;
				warlordsController.showGameView(); //Creates level 2 with settings specified in this case
				break;
			case 3:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 1;
				Game.reverseControls = false;
				GameViewController.gameSpeed = 12;
				warlordsController.showGameView(); //Creates level 3 with settings specified in this case
				break;
			case 4:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 2;
				Game.reverseControls = false;
				GameViewController.gameSpeed = 12;
				warlordsController.showGameView(); //Creates level 4 with settings specified in this case
				break;
			case 5:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 2;
				Game.reverseControls = false;
				GameViewController.gameSpeed = 9;
				warlordsController.showGameView(); //Creates level 5 with settings specified in this case
				break;
			case 6:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 2;
				Game.reverseControls = false;
				GameViewController.gameSpeed = 6;
				warlordsController.showGameView(); ///Creates level 6 with settings specified in this case
				break;
			case 7:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 2;
				Game.reverseControls = true;
				GameViewController.gameSpeed = 6;
				warlordsController.showGameView(); //Creates level 7 with settings specified in this case
				break;
			case 8:
				scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
				warlordsController.createNewGame(0, true, 0, true, 2, false, 0, true);
				Game.randomness = 2;
				Game.reverseControls = true;
				GameViewController.gameSpeed = 6;
				warlordsController.showGameView(); //Creates level 7 with settings specified in this case
				break;				
			}
		} else if (isSelected == 1) {
			//Reset to introduction (level 0)
			MainMenuViewController.isCampaign = true;
			MainMenuViewController.level = 0;
			scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
			warlordsController.showStory0View();
		} else if (isSelected == 2) {
			scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
			warlordsController.showMainMenu();
		} else if (isSelected == 3) {
			scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
			warlordsController.showMainMenu();
		} else {
			scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
			System.out.println("Unavailable Menu Option");
		}
	}
	
	// Text
	@FXML
	private Text title;
	@FXML
	private Text cont;
	@FXML
	private Text newCampaign;
	@FXML
	private Text levelSelect;
	@FXML
	private Text back;
	@FXML
	private Text prompt;
}
