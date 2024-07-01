package gremlins.setup;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class responsible for loading and managing game images.
 */
public class ImageLoader {
    private PApplet sketch;

    // Declare all image variables used in the game
    public PImage stonewall;
    public PImage brickwall;
    public PImage brickwall_destroyed0;
    public PImage brickwall_destroyed1;
    public PImage brickwall_destroyed2;
    public PImage brickwall_destroyed3;
    public PImage gremlin;
    public PImage slime;
    public PImage wizard0;
    public PImage wizard1;
    public PImage wizard2;
    public PImage wizard3;
    public PImage fireball;

    /**
     * Constructor for ImageLoader.
     * @param sketch The PApplet sketch instance
     */
    public ImageLoader(PApplet sketch) {
        this.sketch = sketch;
        loadImages(); // Load all images upon initialization
    }

    /**
     * Loads all images from their respective files.
     */
    private void loadImages() {
        // Load each image using loadImage method
        stonewall = loadImage("stonewall.png");
        brickwall = loadImage("brickwall.png");
        brickwall_destroyed0 = loadImage("brickwall_destroyed0.png");
        brickwall_destroyed1 = loadImage("brickwall_destroyed1.png");
        brickwall_destroyed2 = loadImage("brickwall_destroyed2.png");
        brickwall_destroyed3 = loadImage("brickwall_destroyed3.png");
        gremlin = loadImage("gremlin.png");
        slime = loadImage("slime.png");
        wizard0 = loadImage("wizard0.png");
        wizard1 = loadImage("wizard1.png");
        wizard2 = loadImage("wizard2.png");
        wizard3 = loadImage("wizard3.png");
        fireball = loadImage("fireball.png");
    }

    /**
     * Loads an image from the file path relative to the sketch.
     * @param filename The filename of the image to load
     * @return The loaded PImage object
     */
    private PImage loadImage(String filename) {
        // Construct the file path and load the image
        String filePath = sketch.getClass().getResource(filename).getPath().replace("%20", " ");
        return sketch.loadImage(filePath);
    }
}
