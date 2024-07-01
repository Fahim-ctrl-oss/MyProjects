package gremlins;

import processing.core.PApplet;
import processing.data.JSONObject;

import gremlins.gameObjects.*;
import gremlins.projectiles.*;
import gremlins.setup.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Manages game objects, including walls, wizard, gremlins, projectiles, and game levels.
 */
public class GameObjectManager {

    private PApplet parent; // Reference to the main PApplet
    private ImageLoader imgDir; // Handles loading images
    private Configuration config; // Game configuration

    // Lists to store various game objects
    private List<Wall> stone_wall = new ArrayList<>();
    private List<Wall> brick_wall = new ArrayList<>();
    private Wizard wizard; // The player's wizard character
    private List<Gremlin> gremlin_list = new ArrayList<>(); // List of enemy gremlins
    private Wall door; // Represents the exit door
    private Wizard numOfLives; // Tracks the number of lives (possibly a typo?)

    private List<Drawable> projectile_list = new ArrayList<>(); // List of projectiles

    private int totalLevel = -1; // Total number of levels in the game
    private int currentLevel = -1; // Current level being played
    private Random random = new Random(); // Random number generator
    long startTime = System.currentTimeMillis(); // Start time of the game

    public boolean gameOver = false; // Indicates if the game is over

    /**
     * Constructor for GameObjectManager.
     * @param parent The main PApplet instance
     */
    public GameObjectManager(PApplet parent) {
        this.parent = parent;
    }
    

    /**
     * Loads all necessary images using the ImageLoader class.
     */
    public void loadAllImages() {
        try {
            imgDir = new ImageLoader(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads game configurations from a JSON file and initializes necessary variables.
     */
    public void loadConfigurations() {
        try {
            // Load configuration JSON file
            JSONObject conf = parent.loadJSONObject(new File(Constants.configPath));
            config = new Configuration(conf);

            // Initialize total levels and set current level to 0
            totalLevel = config.Level_Configuration.size();
            currentLevel = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs game objects based on the current level's layout configuration.
     */
    public void makeGameObjects() {
        HashMap<Integer, char[]> layout = config.Level_Configuration.get(currentLevel).getLayout();
        for (int i = 0; i < layout.size(); i++) {
            for (int j = 0; j < layout.get(i).length; j++) {
                switch (layout.get(i)[j]) {
                    case 'X':
                        stone_wall.add(new Wall(imgDir.stonewall, j, i, imgDir));
                        break;
                    case 'B':
                        brick_wall.add(new Wall(imgDir.brickwall, j, i, imgDir));
                        break;
                    case 'G':
                        gremlin_list.add(new Gremlin(imgDir.gremlin, j, i, imgDir));
                        break;
                    case 'E':
                        door = new Wall(j, i); // Create exit door
                        break;
                    case 'W':
                        wizard = new Wizard(imgDir.wizard0, j, i, imgDir); // Initialize player wizard
                        break;
                }
            }
        }
        // If door exists, initialize player lives near the door
        if (door != null) {
            numOfLives = new Wizard(imgDir.wizard1, door.getX(), door.getY(), imgDir);
        }
    }

    /**
     * Draws all game objects on the screen.
     * @param sketch The PApplet sketch to draw on
     */
    public void drawObjects(PApplet sketch) {
        removeDestroyedWall();
        removeProjectilesAndGremlin(sketch);
        moveGremlin();

        // Draw wizard if exists
        if (wizard != null) {
            wizard.draw(sketch);
        }

        // Draw all lists of game objects
        drawList(stone_wall);
        drawList(brick_wall);
        drawList(gremlin_list);
        drawList(projectile_list);

        respawn(); // Check and handle respawning of gremlins
        gameOver(); // Check and handle game over condition
    }

    /**
     * Respawn gremlins randomly after a certain interval.
     */
    public void respawn() {
        int randomNumber = random.nextInt(1000);
        // Arbitrary respawn condition (e.g., every 551 random number)
        if (randomNumber == 551) {
            for (Gremlin obj: gremlin_list) {
                obj.isDead = false; // Reset gremlin status to alive
            }
        }
    }

    /**
     * Draws a list of drawable objects, skipping those marked as dead.
     * @param objects The list of drawable objects to draw
     */
    private void drawList(List<? extends Drawable> objects) {
        for (Drawable obj : objects) {
            if (!obj.getIsDead()) {
                obj.draw(parent);
            }
        }
    }

    /**
     * Draws the bottom bar displaying player lives and current level information.
     */
    public void drawBottomBar() {
        parent.textSize(20);
        parent.fill(255);
        parent.text("Lives:", 10, Constants.HEIGHT - 25);

        int width = Constants.widthOfNumOfLives;
        for (int i = 0; i < config.lives; i++) {
            numOfLives.draw(parent, width, Constants.HEIGHT - 40);
            width += 20;
        }

        String text_level = "Level " + (currentLevel + 1) + "/" + totalLevel;
        parent.textSize(20);
        parent.fill(255);
        parent.text(text_level, Constants.widthOfNumOfLives + Constants.WIDTH - 200, Constants.HEIGHT - 25);
    }

    /**
     * Draws the exit door on the screen.
     */
    public void drawExit() {
        parent.fill(255);
        parent.text("EXIT", (float)(door.getX() * 20), (float)(door.getY() * 20));
        parent.rect((float)(door.getX() * 20), (float)(door.getY() * 20), 20, 20);
    }
    
    /**
     * Checks for collision of the wizard with walls at specified coordinates.
     * @param nextX The next x-coordinate to check
     * @param nextY The next y-coordinate to check
     * @param object The game object (wizard) to check collisions for
     * @return 0 if no collision, 1 if collision with stone wall, 2 if collision with brick wall
     */
    public int collisionWithWall(double nextX, double nextY, GameObject object) {
        int return_val = 0; // 0 = no collision, 1 = collision with stone_wall, 2 = collision with brick_wall
        double wizStartX = (nextX*object.getWidth())/object.getWidth();
        double wizStartY = (nextY*object.getHeight())/object.getHeight();
        
        for (GameObject obj : this.stone_wall) {     
            if ((obj.getX() == Math.floor(wizStartX) && (obj.getY() == Math.floor(wizStartY)))) {
                return_val = 1;
            }
            else if ((obj.getX() == Math.ceil(wizStartX) && (obj.getY() == Math.ceil(wizStartY)))) {
                return_val = 1;
            }
        }
        for (GameObject obj : this.brick_wall) {     
            if ((obj.getX() == Math.floor(wizStartX) && (obj.getY() == Math.floor(wizStartY)))) {
                return_val = 2;
            }
            else if ((obj.getX() == Math.ceil(wizStartX) && (obj.getY() == Math.ceil(wizStartY)))) {
                return_val = 2;
            }
        }
        return return_val; 
    }

    /**
     * Removes destroyed walls from the brick wall list.
     */
    public void removeDestroyedWall() {
        List<Wall> objectsToRemove = new ArrayList<>();

        for (Wall obj : brick_wall) {
            if (obj.isDestroyed()) {
                objectsToRemove.add(obj);
            }
        }
        brick_wall.removeAll(objectsToRemove);
    }

    /**
     * Removes projectiles that have collided with walls or gremlins from the game.
     * @param sketch The PApplet sketch to draw on
     */
    public void removeProjectilesAndGremlin(PApplet sketch) {
        List<Drawable> objectsToRemove = new ArrayList<>();

        for (Drawable obj : projectile_list) {
            double[] nextCoords = obj.nextCoordinates(); 
            
            int collision = collisionWithWall(nextCoords[0], nextCoords[1], (GameObject) this.wizard);
            if (collision != 0) {
                if (collision == 2) {
                    destroyWall(nextCoords, sketch);
                }
                objectsToRemove.add(obj); 
            }

            for (Gremlin gremObj : gremlin_list) {
                if ((Math.floor(nextCoords[0]) == Math.floor(gremObj.getX()) && (Math.floor(nextCoords[1]) == Math.floor(gremObj.getY())))) {
                    if (gremObj.getIsDead()) {continue;}
                    objectsToRemove.add(obj); 
                    gremObj.isDead = true;
                }
                else if ((Math.ceil(nextCoords[1]) == Math.ceil(gremObj.getX()) && (Math.ceil(nextCoords[1]) == Math.ceil(gremObj.getY())))) {
                    if (gremObj.getIsDead()) {continue;}
                    objectsToRemove.add(obj); 
                    gremObj.isDead = true;
                }
            }
        }
        projectile_list.removeAll(objectsToRemove);
    }

    /**
     * Destroys a brick wall at specified coordinates.
     * @param wallCoords The coordinates of the wall to destroy
     * @param sketch The PApplet sketch to draw on
     */
    public void destroyWall(double[] wallCoords, PApplet sketch) {        
        for (Wall obj : brick_wall) {
            if ((obj.getX() == Math.floor(wallCoords[0]) && (obj.getY() == Math.floor(wallCoords[1])))) {
                obj.destroy();
                break;
            }
            else if ((obj.getX() == Math.ceil(wallCoords[0]) && (obj.getY() == Math.ceil(wallCoords[1])))) {
                obj.destroy();
                break;
            }
        }
    }

    /**
     * Moves the wizard character in the specified direction if no collision is detected.
     * @param direction The direction in which to move the wizard
     */
    public void moveWizard(Direction direction) {
        double nextX = 0, nextY = 0;

        switch (direction) {
            case LEFT:
                nextX = this.wizard.next_left_x();
                nextY = this.wizard.next_left_y();
                break;
            case RIGHT:
                nextX = this.wizard.next_right_x();
                nextY = this.wizard.next_right_y();
                break;
            case UP:
                nextX = this.wizard.next_up_x();
                nextY = this.wizard.next_up_y();
                break;
            case DOWN:
                nextX = this.wizard.next_down_x();
                nextY = this.wizard.next_down_y();
                break;
        }
        // Move wizard if no collision detected
        if (collisionWithWall(nextX, nextY, (GameObject) this.wizard) == 0) {
           switch (direction) {
                case LEFT:
                    this.wizard.left();
                    break;
                case RIGHT:
                    this.wizard.right();
                    break;
                case UP:
                    this.wizard.up();
                    break;
                case DOWN:
                    this.wizard.down();
                    break;
            }
        }
    }

    /**
     * Moves all gremlins in the game.
     */
    public void moveGremlin() {
        // Generate a random delay for each gremlin
        int randomDelay = random.nextInt(401) + 100;
        long currentTime = System.currentTimeMillis();

        // Iterate through all gremlins
        for (Gremlin obj : gremlin_list) {
            // Check if it's time for the gremlin to change direction
            if (currentTime - startTime >= randomDelay) {
                obj.setDirection();
            }
            // Ensure gremlins do not move into walls
            while (collisionWithWall(obj.nextCoordinates()[0], obj.nextCoordinates()[1], obj) != 0) {
                obj.setDirection();
            }
            // Move the gremlin
            obj.move();
        }
        // Update start time for the next cycle
        startTime = currentTime;
    }

    /**
     * Handles game over conditions based on player and gremlin positions.
     */
    public void gameOver() {
        // Check if the wizard reaches the exit door
        if (wizard.getX() == door.getX() && wizard.getY() == door.getY()) {
            currentLevel++; // Move to the next level
            if (currentLevel == totalLevel) {
                gameOver = true; // Game over if all levels are completed
            } else {
                makeGameObjects(); // Load game objects for the next level
            }
        }

        // Check for collision between wizard and gremlins
        for (Gremlin obj : gremlin_list) {
            if (obj.getIsDead()) { continue; } // Skip dead gremlins
            if (wizard.getX() == obj.getX() && wizard.getY() == obj.getY()) {
                if (config.lives == 0) {
                    gameOver = true; // Game over if no lives left
                }
                config.lives--; // Decrease lives
                break; // Exit loop after handling collision
            }
        }
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Handles left arrow key press event.
     */
    public void left_pressed() {
        wizard.setImg(imgDir.wizard0, Direction.LEFT); // Set wizard image for left direction
        moveWizard(Direction.LEFT); // Move wizard left
    }

    /**
     * Handles right arrow key press event.
     */
    public void right_pressed() {
        wizard.setImg(imgDir.wizard1, Direction.RIGHT); // Set wizard image for right direction
        moveWizard(Direction.RIGHT); // Move wizard right
    }

    /**
     * Handles up arrow key press event.
     */
    public void up_pressed() {
        wizard.setImg(imgDir.wizard2, Direction.UP); // Set wizard image for up direction
        moveWizard(Direction.UP); // Move wizard up
    }

    /**
     * Handles down arrow key press event.
     */
    public void down_pressed() {
        wizard.setImg(imgDir.wizard3, Direction.DOWN); // Set wizard image for down direction
        moveWizard(Direction.DOWN); // Move wizard down
    }

    /**
     * Stops the wizard from moving.
     */
    public void stop() {
        wizard.stop(); // Stop wizard movement
    }

    /**
     * Fires a projectile from the wizard if conditions allow.
     */
    public void shoot() {
        // Limit the number of projectiles on screen
        if (projectile_list.size() < 2) {
            // Create a new projectile and add it to the projectile list
            Drawable projectile_obj = new Projectile(imgDir.fireball, wizard.getX(), wizard.getY(), wizard.getDirection());
            projectile_list.add(projectile_obj);
        }
    }

}
