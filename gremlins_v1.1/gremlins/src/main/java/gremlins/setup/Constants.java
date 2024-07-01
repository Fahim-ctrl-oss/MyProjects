package gremlins.setup;

import java.util.Random;

public class Constants {
    // Game dimensions
    public static final int WIDTH = 720; // Width of the game window
    public static final int HEIGHT = 720; // Height of the game window
    public static final int SPRITESIZE = 20; // Size of each sprite or tile in pixels
    public static final int BOTTOMBAR = 60; // Height of the bottom bar area

    // Game settings
    public static final int FPS = 360; // Frames per second for smooth animation
    public static final int initialTextTimer = FPS * 2; // Initial timer for displaying text (2 seconds)

    // Random number generator
    public static final Random randomGenerator = new Random(); // Random number generator instance

    // Configuration file path
    public static final String configPath = "config.json"; // Path to the game configuration JSON file

    // Symbols used in the game
    public static final char[] symbols = {'X', 'B', 'W', 'E', 'G'};
    // 'X' - Stone wall, 'B' - Brick wall, 'W' - Wizard, 'E' - Exit door, 'G' - Gremlin

    // Width offset for displaying number of lives
    public static int widthOfNumOfLives = 70; // Initial width position for displaying number of lives
}
