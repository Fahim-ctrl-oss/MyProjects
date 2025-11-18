package gremlins.gameObjects;

import gremlins.setup.ImageLoader;
import gremlins.setup.Direction;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

/**
 * Represents a gremlin game object that can be drawn and moved.
 */
public class Gremlin implements GameObject, Drawable, Movement {
    // Constants for default width and height
    private final int width = 20;
    private final int height = 20;
    private int time = 3; 
    private double speed = 0.5;

    // Position and direction variables
    private double x;
    private double y;

    // Image represention
    private PImage wall;
    private ImageLoader imgDir;
    private Direction direction;
    public boolean isDead = false;

    private Random random = new Random();
    private Direction[] directions = Direction.values();

    /**
     * Constructor to initialize a Gremlin with image, position, and image loader.
     *
     * @param img    Image of the gremlin
     * @param x      Initial x-coordinate of the gremlin
     * @param y      Initial y-coordinate of the gremlin
     * @param imgDir Image loader for managing images
     */
    public Gremlin(PImage img, double x, double y, ImageLoader imgDir) {
        this.wall = img;
        this.x = x;
        this.y = y;
        this.imgDir = imgDir;

        setDirection();
    }

    /**
     * Get the current x-coordinate of the gremlin.
     *
     * @return The current x-coordinate
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Get the current y-coordinate of the gremlin.
     *
     * @return The current y-coordinate
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Get the width of the gremlin.
     *
     * @return The width of the gremlin
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the gremlin.
     *
     * @return The height of the gremlin
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Draw the gremlin on the screen using the provided Processing PApplet.
     *
     * @param app The Processing PApplet used for drawing
     */
    @Override
    public void draw(PApplet app) {
        double xCoord = this.x * width;
        double yCoord = this.y * height;

        app.image(this.wall, (float) xCoord, (float) yCoord);

        // Update coordinates based on drawing position
        this.x = xCoord / width;
        this.y = yCoord / height;
    }

    /**
     * Get the image of the gremlin.
     *
     * @return The image of the gremlin
     */
    @Override
    public PImage getImg() {
        return this.wall;
    }

    /**
     * Set the image of the gremlin.
     *
     * @param img New image for the gremlin
     */
    @Override
    public void setImg(PImage img) {
        this.wall = img;
    }

    /**
     * Move the gremlin to the right.
     */
    @Override
    public void right() {
        // Example implementation, uncomment to enable movement
        // this.x += this.speed;
    }

    /**
     * Move the gremlin to the left.
     */
    @Override
    public void left() {
        // Example implementation, uncomment to enable movement
        // this.x -= this.speed;
    }

    /**
     * Move the gremlin down.
     */
    @Override
    public void down() {
        // Example implementation, uncomment to enable movement
        // this.y += this.speed;
    }

    /**
     * Move the gremlin up.
     */
    @Override
    public void up() {
        // Example implementation, uncomment to enable movement
        // this.y -= this.speed;
    }

    /**
     * Stop the gremlin's movement.
     */
    @Override
    public void stop() {
        // Example implementation, can be left empty if not needed
    }

    /**
     * Calculate the next x-coordinate when moving right.
     *
     * @return The next x-coordinate when moving right
     */
    @Override
    public double next_right_x() {
        return (this.x + this.speed);
    }

    /**
     * Calculate the next y-coordinate when moving right.
     *
     * @return The next y-coordinate when moving right
     */
    @Override
    public double next_right_y() {
        return (this.y);
    }

    /**
     * Calculate the next x-coordinate when moving left.
     *
     * @return The next x-coordinate when moving left
     */
    @Override
    public double next_left_x() {
        return (this.x - this.speed);
    }

    /**
     * Calculate the next y-coordinate when moving left.
     *
     * @return The next y-coordinate when moving left
     */
    @Override
    public double next_left_y() {
        return (this.y);
    }

    /**
     * Calculate the next y-coordinate when moving down.
     *
     * @return The next y-coordinate when moving down
     */
    @Override
    public double next_down_y() {
        return (this.y + this.speed);
    }

    /**
     * Calculate the next x-coordinate when moving down.
     *
     * @return The next x-coordinate when moving down
     */
    @Override
    public double next_down_x() {
        return (this.x);
    }

    /**
     * Calculate the next y-coordinate when moving up.
     *
     * @return The next y-coordinate when moving up
     */
    @Override
    public double next_up_y() {
        return (this.y - this.speed);
    }

    /**
     * Calculate the next x-coordinate when moving up.
     *
     * @return The next x-coordinate when moving up
     */
    @Override
    public double next_up_x() {
        return (this.x);
    }

    /**
     * Check if the gremlin is dead.
     *
     * @return true if the gremlin is dead, false otherwise
     */
    @Override
    public boolean getIsDead() {
        return this.isDead;
    }

    /**
     * Calculate the next coordinates of the gremlin based on its current direction.
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

    /**
     * Randomly set the direction of the gremlin.
     */
    public void setDirection() {
        if (this.direction == Direction.UP || this.direction == Direction.LEFT) {
            if (this.random.nextInt(2) == 1) {
                this.direction = Direction.LEFT;
            } else {
                this.direction = Direction.RIGHT;
            }
        } else if (this.direction == Direction.DOWN || this.direction == Direction.RIGHT) {
            if (this.random.nextInt(2) == 1) {
                this.direction = Direction.UP;
            } else {
                this.direction = Direction.DOWN;
            }
        } else {
            Direction temp = directions[this.random.nextInt(this.directions.length)];
            while (temp == this.direction) {
                temp = directions[this.random.nextInt(this.directions.length)];
            }
            this.direction = temp;
        }
    }

    /**
     * Move the gremlin according to its current direction and speed.
     */
    public void move() {
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

        // Update gremlin's position
        this.x = xCoord / width;
        this.y = yCoord / height;
    }

    /**
     * Get the current time attribute of the gremlin.
     *
     * @return The current time attribute value
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Set the time attribute of the gremlin.
     *
     * @param t New value for the time attribute
     */
    public void setTime(int t) {
        this.time = t;
    }
}
