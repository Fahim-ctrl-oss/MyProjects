package gremlins;

import processing.core.PApplet;
import processing.event.KeyEvent;

import gremlins.setup.Constants;
import gremlins.setup.ImageLoader;
import gremlins.setup.Configuration;
import gremlins.setup.LevelDetails;
import gremlins.gameObjects.*;

/**
 * The main application class for the Gremlins game.
 * Extends PApplet to utilize Processing's drawing and event handling functionalities.
 */
public class App extends PApplet {

    private GameObjectManager gameObjectManager; // Manages game objects and their interactions
    private Controller controller; // Handles user input and game control

    /**
     * Initializes the size of the application window.
     * Called once when the program starts.
     */
    public void settings() {
        size(Constants.WIDTH, Constants.HEIGHT); // Set the size of the window based on constants
    }

    /**
     * Sets up initial configurations and game objects.
     * Called once when the program starts.
     */
    public void setup() {
        frameRate(Constants.FPS); // Set the frame rate of the game
        
        // Initialize GameObjectManager and load necessary resources
        gameObjectManager = new GameObjectManager(this);
        gameObjectManager.loadAllImages();
        gameObjectManager.loadConfigurations();
        gameObjectManager.makeGameObjects();

        // Initialize the controller to handle user input
        this.controller = new Controller(this, gameObjectManager); 
    }

    /**
     * Main drawing loop.
     * Called repeatedly to update and render the game state.
     */
    public void draw() {
        background(194, 164, 135); // Set background color
        
        // Check if restart flag is set
        if (this.controller.getRestart()) {
            gameObjectManager.gameOver = false; // Reset game over flag
            this.controller.setRestart(); // Clear restart flag
            setup(); // Restart the game
        }
        
        // Display game over message if game is over
        if (gameObjectManager.isGameOver()) {
            fill(255); // Set text color to white
            textAlign(CENTER); // Center align text
            text("GAME OVER!!! Press R to Restart", Constants.WIDTH/2, Constants.HEIGHT/2); // Display game over message
            return; // Exit draw method early if game over
        }
        
        // Draw game objects, exit, and bottom bar
        gameObjectManager.drawObjects(this); // Draw all game objects
        gameObjectManager.drawExit(); // Draw exit area
        gameObjectManager.drawBottomBar(); // Draw bottom bar UI
    }

    /**
     * Main method to launch the application.
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        PApplet.main("gremlins.App"); // Launch the Processing application
    }
}
