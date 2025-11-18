package gremlins;

import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * Controller class handles keyboard events for controlling game actions.
 * It listens for key presses and releases, directing corresponding actions
 * to the GameObjectManager.
 */
public class Controller {

    private PApplet parent; // The main PApplet instance where events are handled
    private GameObjectManager gameObjectManager; // Manages game objects and their actions
    private boolean restart = false; // Flag indicating if game restart is requested

    /**
     * Constructor for Controller.
     *
     * @param parent            The PApplet instance where events are handled
     * @param gameObjectManager The GameObjectManager to control game objects
     */
    public Controller(PApplet parent, GameObjectManager gameObjectManager) {
        this.parent = parent;
        this.gameObjectManager = gameObjectManager;

        // Registering this controller as the KeyEvent listener
        parent.registerMethod("keyEvent", this);
    }

    /**
     * Sets the restart flag to false.
     * This method is called to indicate that a restart has been handled.
     */
    public void setRestart() {
        this.restart = false;
    }

    /**
     * Gets the restart flag.
     *
     * @return true if restart is requested, false otherwise
     */
    public boolean getRestart() {
        return this.restart;
    }

    /**
     * Handles key events from Processing.
     * This method is invoked automatically by Processing when a key event occurs.
     *
     * @param k The KeyEvent object containing key event details
     */
    public void keyEvent(KeyEvent k) {
        if (k.getAction() == KeyEvent.PRESS) {
            // Call keyPressed method to handle key press actions
            keyPressed(k);
        } else if (k.getAction() == KeyEvent.RELEASE) {
            // Call keyReleased method to handle key release actions
            keyReleased(k);
        }
    }

    /**
     * Handles key press events.
     *
     * @param k The KeyEvent object containing key press details
     */
    private void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();
        switch (key) {
            case 37:  // Left arrow key
            case 65:  // 'A' key
                gameObjectManager.left_pressed();
                break;

            case 39:  // Right arrow key
            case 68:  // 'D' key
                gameObjectManager.right_pressed();
                break;

            case 38:  // Up arrow key
            case 87:  // 'W' key
                gameObjectManager.up_pressed();
                break;

            case 40:  // Down arrow key
            case 83:  // 'S' key
                gameObjectManager.down_pressed();
                break;

            case 32:  // ' ' key (spacebar)
                gameObjectManager.shoot();
                break;

            case 82:  // 'R' key
                this.restart = true;
                break;

            default:
                break;
        }
    }

    /**
     * Handles key release events.
     *
     * @param k The KeyEvent object containing key release details
     */
    private void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();
        switch (key) {
            case 37:  // Left arrow key
            case 65:  // 'A' key
            case 39:  // Right arrow key
            case 68:  // 'D' key
            case 38:  // Up arrow key
            case 87:  // 'W' key
            case 40:  // Down arrow key
            case 83:  // 'S' key
                // Stop movement when arrow keys or WASD keys are released
                gameObjectManager.stop();
                break;

            default:
                break;
        }
    }
}
