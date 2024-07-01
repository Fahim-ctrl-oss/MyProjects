package gremlins.gameObjects;

import processing.core.PApplet;

/**
 * Represents an object that can be drawn on screen.
 */
public interface Drawable {

    /**
     * Draws the object using the provided Processing PApplet.
     *
     * @param p The Processing PApplet used for drawing
     */
    void draw(PApplet p);

    /**
     * Checks if the object is dead or no longer active.
     *
     * @return true if the object is dead, false otherwise
     */
    boolean getIsDead();

    /**
     * Calculates the next coordinates of the object based on its internal state.
     *
     * @return Array containing the next x and y coordinates of the object
     */
    double[] nextCoordinates();
}
