package gremlins.gameObjects;

import gremlins.setup.ImageLoader;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a wall game object that can be drawn and potentially destroyed.
 */
public class Wall implements GameObject, Drawable {

    private final int width = 20;
    private final int height = 20;
    private int time = 3; // Time for destruction animation

    private double x;
    private double y;

    private PImage wall;
    private ImageLoader imgDir;

    private boolean destroy = false; // Flag indicating if the wall is being destroyed
    private long lastDestroyTime = 0; // Last time destruction animation was updated
    public boolean isDead = false;

    /**
     * Constructs a wall with an image, position, and image loader.
     *
     * @param img    Image of the wall
     * @param x      Initial x-coordinate of the wall
     * @param y      Initial y-coordinate of the wall
     * @param imgDir Image loader for managing images
     */
    public Wall(PImage img, double x, double y, ImageLoader imgDir) {
        this.wall = img;
        this.x = x;
        this.y = y;
        this.imgDir = imgDir;
    }

    /**
     * Constructs a wall with specified coordinates (for non-image walls).
     *
     * @param x Initial x-coordinate of the wall
     * @param y Initial y-coordinate of the wall
     */
    public Wall(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the current x-coordinate of the wall.
     *
     * @return The current x-coordinate
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Get the current y-coordinate of the wall.
     *
     * @return The current y-coordinate
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Get the width of the wall.
     *
     * @return The width of the wall
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the wall.
     *
     * @return The height of the wall
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Draw the wall on the screen using the provided Processing PApplet.
     *
     * @param app The Processing PApplet used for drawing
     */
    @Override
    public void draw(PApplet app) {
        double xCoord = this.x * width;
        double yCoord = this.y * height;
        animateDestruction();
        app.image(this.wall, (float) xCoord, (float) yCoord);
    }

    /**
     * Animate the destruction of the wall if it is marked for destruction.
     * This method updates the wall's image to simulate destruction over time.
     */
    public void animateDestruction() {
        if (!destroy) {
            return;
        }
        switch (this.time) {
            case 3:
                this.setImg(this.imgDir.brickwall_destroyed0);
                break;
            case 2:
                this.setImg(this.imgDir.brickwall_destroyed1);
                break;
            case 1:
                this.setImg(this.imgDir.brickwall_destroyed2);
                break;
            case 0:
                this.setImg(this.imgDir.brickwall_destroyed3);
                this.destroy = false;
                break;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastDestroyTime >= 200) {
            this.setTime(this.getTime() - 1);
            this.lastDestroyTime = currentTime;
        }
    }

    /**
     * Mark the wall for destruction.
     * This initiates the destruction animation.
     */
    public void destroy() {
        this.destroy = true;
    }

    /**
     * Check if the wall has been completely destroyed.
     *
     * @return true if the wall is completely destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return this.getTime() <= 0;
    }

    /**
     * Get the image of the wall.
     *
     * @return The image of the wall
     */
    @Override
    public PImage getImg() {
        return this.wall;
    }

    /**
     * Set the image of the wall.
     *
     * @param img New image for the wall
     */
    @Override
    public void setImg(PImage img) {
        this.wall = img;
    }

    /**
     * Check if the wall is marked as dead.
     *
     * @return true if the wall is marked as dead, false otherwise
     */
    @Override
    public boolean getIsDead() {
        return this.isDead;
    }

    /**
     * Calculate the next coordinates of the wall (not applicable for non-moving walls).
     *
     * @return Array containing the next x and y coordinates (always [0.0, 0.0] for walls)
     */
    public double[] nextCoordinates() {
        return new double[]{0.0, 0.0}; // Not applicable for static walls
    }

    /**
     * Get the remaining time for destruction animation.
     *
     * @return The remaining time for destruction animation
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Set the time for destruction animation.
     *
     * @param t New time value for destruction animation
     */
    public void setTime(int t) {
        this.time = t;
    }
}
