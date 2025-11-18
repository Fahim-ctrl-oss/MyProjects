package gremlins.gameObjects;

/**
 * Interface for defining movement behavior.
 */
public interface Movement {

    /**
     * Move up.
     */
    void up();

    /**
     * Move down.
     */
    void down();

    /**
     * Move left.
     */
    void left();

    /**
     * Move right.
     */
    void right();

    /**
     * Stop movement and move character to middle of that particular square.
     */
    void stop();

    /**
     * Calculate the next x-coordinate when moving up.
     *
     * @return The next x-coordinate when moving up
     */
    double next_up_x();

    /**
     * Calculate the next y-coordinate when moving up.
     *
     * @return The next y-coordinate when moving up
     */
    double next_up_y();

    /**
     * Calculate the next x-coordinate when moving down.
     *
     * @return The next x-coordinate when moving down
     */
    double next_down_x();

    /**
     * Calculate the next y-coordinate when moving down.
     *
     * @return The next y-coordinate when moving down
     */
    double next_down_y();

    /**
     * Calculate the next x-coordinate when moving left.
     *
     * @return The next x-coordinate when moving left
     */
    double next_left_x();

    /**
     * Calculate the next y-coordinate when moving left.
     *
     * @return The next y-coordinate when moving left
     */
    double next_left_y();

    /**
     * Calculate the next x-coordinate when moving right.
     *
     * @return The next x-coordinate when moving right
     */
    double next_right_x();

    /**
     * Calculate the next y-coordinate when moving right.
     *
     * @return The next y-coordinate when moving right
     */
    double next_right_y();
}
