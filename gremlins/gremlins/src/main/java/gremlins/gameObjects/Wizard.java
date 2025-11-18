package gremlins.gameObjects;

import gremlins.setup.ImageLoader;
import gremlins.setup.Direction;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a wizard game object that can be drawn and moved.
 */
public class Wizard implements GameObject, Drawable, Movement {

    // Dimensions of the wizard image
    private final int width = 20;
    private final int height = 20;

    // Time for any time related to the wizard
    private int time = 3;

    // Current position of the wizard
    private double x;
    private double y;

    // Current direction and movement speed of the wizard
    private Direction direction;
    private double speed = 1;

    // Image of the wizard and image loader for managing images
    private PImage wiz;
    private ImageLoader imgDir;

    // Flag indicating if the wizard is dead
    public boolean isDead = false;

    /**
     * Constructs a wizard with an image, position, and image loader.
     *
     * @param img    Image of the wizard
     * @param x      Initial x-coordinate of the wizard
     * @param y      Initial y-coordinate of the wizard
     * @param imgDir Image loader for managing images
     */
    public Wizard(PImage img, double x, double y, ImageLoader imgDir) {
        this.wiz = img;
        this.x = x;
        this.y = y;
        this.imgDir = imgDir;
        this.direction = Direction.LEFT; // Initial direction is left
    }

    /**
     * Get the current x-coordinate of the wizard.
     *
     * @return The current x-coordinate
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Get the current y-coordinate of the wizard.
     *
     * @return The current y-coordinate
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Get the width of the wizard.
     *
     * @return The width of the wizard
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the wizard.
     *
     * @return The height of the wizard
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Draw the wizard on the screen using the provided Processing PApplet.
     *
     * @param app The Processing PApplet used for drawing
     */
    @Override
    public void draw(PApplet app) {
        double x_coord = this.x * width;
        double y_coord = this.y * height;
        app.image(this.wiz, (float) x_coord, (float) y_coord);
    }

    /**
     * Draw the wizard at a specific position on the screen.
     *
     * @param app   The Processing PApplet used for drawing
     * @param width X-coordinate where the wizard should be drawn
     * @param height Y-coordinate where the wizard should be drawn
     */
    public void draw(PApplet app, int width, int height) {
        app.image(this.wiz, width, height);
    }

    /**
     * Check if the wizard is marked as dead.
     *
     * @return true if the wizard is marked as dead, false otherwise
     */
    @Override
    public boolean getIsDead() {
        return this.isDead;
    }

    /**
     * Get the image of the wizard.
     *
     * @return The image of the wizard
     */
    @Override
    public PImage getImg() {
        return this.wiz;
    }

    /**
     * Set the image of the wizard.
     *
     * @param img New image for the wizard
     */
    @Override
    public void setImg(PImage img) {
        this.wiz = img;
    }

    /**
     * Set the image and direction of the wizard.
     *
     * @param img       New image for the wizard
     * @param direction New direction for the wizard
     */
    public void setImg(PImage img, Direction direction) {
        this.wiz = img;
        this.direction = direction;
    }

    /**
     * Move the wizard to the right.
     */
    @Override
    public void right() {
        this.x += this.speed;
    }

    /**
     * Move the wizard to the left.
     */
    @Override
    public void left() {
        this.x -= this.speed;
    }

    /**
     * Move the wizard down.
     */
    @Override
    public void down() {
        this.y += this.speed;
    }

    /**
     * Move the wizard up.
     */
    @Override
    public void up() {
        this.y -= this.speed;
    }

    /**
     * Stop the wizard's movement and round its position to nearest integer values.
     */
    @Override
    public void stop() {
        this.x = Math.round(this.x);
        this.y = Math.round(this.y);
    }

    /**
     * Calculate the next x-coordinate if the wizard moves to the right.
     *
     * @return The next x-coordinate if moving to the right
     */
    @Override
    public double next_right_x() {
        return (this.x + this.speed);
    }

    /**
     * Calculate the next y-coordinate if the wizard moves to the right.
     *
     * @return The next y-coordinate if moving to the right
     */
    @Override
    public double next_right_y() {
        return (this.y);
    }

    /**
     * Calculate the next x-coordinate if the wizard moves to the left.
     *
     * @return The next x-coordinate if moving to the left
     */
    @Override
    public double next_left_x() {
        return (this.x - this.speed);
    }

    /**
     * Calculate the next y-coordinate if the wizard moves to the left.
     *
     * @return The next y-coordinate if moving to the left
     */
    @Override
    public double next_left_y() {
        return (this.y);
    }

    /**
     * Calculate the next y-coordinate if the wizard moves down.
     *
     * @return The next y-coordinate if moving down
     */
    @Override
    public double next_down_y() {
        return (this.y + this.speed);
    }

    /**
     * Calculate the next x-coordinate if the wizard moves down.
     *
     * @return The next x-coordinate if moving down
     */
    @Override
    public double next_down_x() {
        return (this.x);
    }

    /**
     * Calculate the next y-coordinate if the wizard moves up.
     *
     * @return The next y-coordinate if moving up
     */
    @Override
    public double next_up_y() {
        return (this.y - this.speed);
    }

    /**
     * Calculate the next x-coordinate if the wizard moves up.
     *
     * @return The next x-coordinate if moving up
     */
    @Override
    public double next_up_x() {
        return (this.x);
    }

    /**
     * Calculate the next coordinates of the wizard (not applicable for wizards).
     *
     * @return Array containing the next x and y coordinates (always [0.0, 0.0] for wizards)
     */
    public double[] nextCoordinates() {
        return new double[]{0.0, 0.0}; // Not applicable for moving wizards
    }

    /**
     * Get the remaining time for any animation related to the wizard.
     *
     * @return The remaining time for wizard-related animation
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Set the time for any animation related to the wizard.
     *
     * @param t New time value for wizard-related animation
     */
    public void setTime(int t) {
        this.time = t;
    }

    /**
     * Get the current direction of the wizard.
     *
     * @return The current direction of the wizard
     */
    public Direction getDirection() {
        return this.direction;
    }

}
