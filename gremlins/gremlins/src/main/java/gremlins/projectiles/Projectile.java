package gremlins.projectiles;

import processing.core.PApplet;
import processing.core.PImage;

import gremlins.gameObjects.GameObject; 
import gremlins.gameObjects.Drawable; 
import gremlins.setup.Direction; 

/**
 * Represents a projectile in the game.
 */
public class Projectile implements GameObject, Drawable {
    // Constants for default width and height of the projectile
    private final int width = 20;
    private final int height = 20;

    // Position and direction variables
    private double x;
    private double y;
    private Direction direction;

    // Image representing the projectile
    private PImage wall;

    // Remaining lives of the projectile
    private int lives = 1;

    // Speed of the projectile
    private double speed = 0.7;

    // Flag indicating if the projectile is dead
    public boolean isDead = false;

    /**
     * Constructor to initialize the projectile with image, position, and direction.
     *
     * @param img       Image of the projectile
     * @param x         Initial x-coordinate of the projectile
     * @param y         Initial y-coordinate of the projectile
     * @param direction Initial direction of the projectile
     */
    public Projectile(PImage img, double x, double y, Direction direction) {
        this.wall = img;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * Get the current x-coordinate of the projectile.
     *
     * @return Current x-coordinate
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Get the current y-coordinate of the projectile.
     *
     * @return Current y-coordinate
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Get the width of the projectile.
     *
     * @return Width of the projectile
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the projectile.
     *
     * @return Height of the projectile
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Draw the projectile on the screen.
     *
     * @param app Processing PApplet used for drawing
     */
    @Override
    public void draw(PApplet app) {
        // Calculate new coordinates based on direction and speed
        double xCoord = this.x * width;
        double yCoord = this.y * height;

        switch (this.direction) {
            case LEFT:
                xCoord -= this.speed;
                break;
            case RIGHT:
                xCoord += this.speed;
                break;
            case UP:
                yCoord -= this.speed;
                break;
            case DOWN:
                yCoord += this.speed;
                break;
        }
         // Draw the projectile image at the new coordinates
        app.image(this.wall, (float) xCoord, (float) yCoord);

        // Update projectile's position
        this.x = xCoord / width;
        this.y = yCoord / height;
    }

    /**
     * Get the image of the projectile.
     *
     * @return Image of the projectile
     */
    @Override
    public PImage getImg() {
        return this.wall;
    }

    /**
     * Set the image of the projectile.
     *
     * @param img New image for the projectile
     */
    @Override
    public void setImg(PImage img) {
        this.wall = img;
    }

    /**
     * Check if the projectile is dead.
     *
     * @return true if the projectile is dead, false otherwise
     */
    @Override
    public boolean getIsDead() {
        return this.isDead;
    }

    /**
     * Calculate the next coordinates of the projectile based on its direction and speed.
     *
     * @return Array containing the next x and y coordinates
     */
    public double[] nextCoordinates() {
        double[] coordinates = new double[2]; 

        switch (this.direction) {
            case UP:
                coordinates[0] = this.x;
                coordinates[1] = ((this.y * width) - this.speed) / width;
                break;
            case DOWN:
                coordinates[0] = this.x;
                coordinates[1] = ((this.y * width) + this.speed) / width;
                break;
            case LEFT:
                coordinates[0] = ((this.x * width) - this.speed) / width;
                coordinates[1] = this.y;
                break;
            case RIGHT:
                coordinates[0] = ((this.x * width) + this.speed) / width;
                coordinates[1] = this.y;
                break;
            default:
                throw new IllegalArgumentException("Unknown direction: " + direction);
        }

        return coordinates;
    }

}