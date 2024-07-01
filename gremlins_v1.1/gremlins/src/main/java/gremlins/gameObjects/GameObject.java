package gremlins.gameObjects;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a game object with basic properties and methods.
 */
public interface GameObject {

    /**
     * Get the x-coordinate of the game object.
     *
     * @return The x-coordinate of the game object
     */
    double getX();

    /**
     * Get the y-coordinate of the game object.
     *
     * @return The y-coordinate of the game object
     */
    double getY();

    /**
     * Get the width of the game object.
     *
     * @return The width of the game object
     */
    double getWidth();

    /**
     * Get the height of the game object.
     *
     * @return The height of the game object
     */
    double getHeight();

    /**
     * Draw the game object on the screen using the provided Processing PApplet.
     *
     * @param app The Processing PApplet used for drawing
     */
    void draw(PApplet app);

    /**
     * Get the image associated with the game object.
     *
     * @return The image of the game object
     */
    PImage getImg();

    /**
     * Set the image associated with the game object.
     *
     * @param img The new image for the game object
     */
    void setImg(PImage img);
}
